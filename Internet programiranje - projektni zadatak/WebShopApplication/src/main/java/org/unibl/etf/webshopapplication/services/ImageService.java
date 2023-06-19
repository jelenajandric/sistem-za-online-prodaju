package org.unibl.etf.webshopapplication.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.webshopapplication.model.GetAllImagesResponse;
import org.unibl.etf.webshopapplication.model.entities.ImageEntity;
import org.unibl.etf.webshopapplication.repositories.ImageRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {
    private static final String IMAGES_PATH = System.getProperty("user.home") + "\\Documents\\products-photos\\";
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public boolean addNewImage(MultipartFile image, int productId) {
        String name = System.currentTimeMillis() + image.getOriginalFilename(); //ovo cuvam u bazi

        String path = IMAGES_PATH + name;
        File f = new File(path);
        try {
            image.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        ImageEntity entity = new ImageEntity();
        entity.setPath(name);
        entity.setProductId(productId);
        imageRepository.save(entity);
        return true;
    }

    public List<GetAllImagesResponse> getAllImagesForProduct(int productId) {
        List<ImageEntity> imageEntities = imageRepository.findAllByProductId(productId);
        List<GetAllImagesResponse> images = new ArrayList<>();

        imageEntities.forEach(imageEntity -> {
            File file = new File(IMAGES_PATH + imageEntity.getPath());
            String encodeImage = null;
            try {
                encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            images.add(new GetAllImagesResponse(imageEntity.getId(), encodeImage));
        });
        return images;
    }

    public String getOneImageForProduct(int productId) {
        String path = imageRepository.findOneByProductId(productId);
        return getImageForPath(path);
    }

    public String getImageForPath(String path) {
        if (path == null) {
            return null;
        }
        File file = new File(IMAGES_PATH + path);
        String encodeImage = null;
        try {
            encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodeImage;
    }

    public void deleteImage(int id) {
        Optional<ImageEntity> entity = imageRepository.findById(id);
        if (entity.isPresent()) {
            File file = new File(IMAGES_PATH + entity.get().getPath());
            file.delete();
            imageRepository.deleteById(id);
        }
    }
}
