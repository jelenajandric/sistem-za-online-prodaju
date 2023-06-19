package org.unibl.etf.webshopapplication.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.unibl.etf.webshopapplication.services.AvatarService;

import java.util.Map;

@RestController
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping("/upload-avatar/{username}")
    public ResponseEntity<Boolean> uplaodImage(@RequestParam("image") MultipartFile file, @PathVariable("username") String username) {
        return ResponseEntity.ok(avatarService.uploadImage(file, username));
    }

    @GetMapping("/get-avatar-by-username/{username}")
    public @ResponseBody Map<String, String> getImage(@PathVariable("username") String username) {
        return avatarService.getAvatarPhoto(username);
    }

    @GetMapping("/delete-avatar/{username}")
    public void deleteAvatarPhoto(@PathVariable("username") String username) {
        avatarService.deleteAvatarPhoto(username);
    }
}
