package org.unibl.etf.webshopapplication.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.webshopapplication.repositories.ClientRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class AvatarService {
    private static final String AVATAR_PATH = System.getProperty("user.home") + "\\Documents\\user-avatars\\";

    private final ClientRepository clientRepository;

    public AvatarService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public boolean uploadImage(MultipartFile image, String username) {
        String name = System.currentTimeMillis() + image.getOriginalFilename(); //ovo cuvam u bazi

        String path = AVATAR_PATH + name;
        File f = new File(path);
        try {
            image.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        clientRepository.updateImage(name, username);
        return true;
    }

    public Map<String, String> getAvatarPhoto(String username) {
        String filePath = clientRepository.findAvatarByUsername(username);
        Map<String, String> jsonMap = new HashMap<>();
        try {
            File file = new File(AVATAR_PATH + filePath);
            if (file.exists()) {
                String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
                jsonMap.put("content", encodeImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonMap;
    }

    public void deleteAvatarPhoto(String username) {
        String filePath = clientRepository.findAvatarByUsername(username);
        File file = new File(AVATAR_PATH + filePath);
        if (file.exists()) {
            file.delete();
        }
        clientRepository.deleteAvatarPhoto(username);
    }
}
