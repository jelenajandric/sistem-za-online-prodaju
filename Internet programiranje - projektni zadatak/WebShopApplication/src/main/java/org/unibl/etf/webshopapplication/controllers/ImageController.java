package org.unibl.etf.webshopapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.webshopapplication.model.GetAllImagesResponse;
import org.unibl.etf.webshopapplication.services.ImageService;

import java.util.List;

@RestController
@RequestMapping("/product-images")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/add-new/{productId}")
    public ResponseEntity<Boolean> addNewImage(@RequestBody MultipartFile image, @PathVariable("productId") int productId) {
        if (imageService.addNewImage(image, productId)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(204).body(false);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<GetAllImagesResponse>> getAllImagesForProduct(@PathVariable("id") int productId) {
        List<GetAllImagesResponse> responses = imageService.getAllImagesForProduct(productId);
        return ResponseEntity.ok(responses);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable("id") int id) {
        imageService.deleteImage(id);
    }

}
