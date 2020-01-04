package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.services.ImageStore;
import com.training.fitnessappserver.services.UserService;
import com.training.fitnessappserver.services.impl.ImageStoreInFileSystem;
import com.training.fitnessappserver.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/users/{id}/photo")
@CrossOrigin(origins = "http://localhost:3000")
public class UserProfilePhotoController {
    private UserService userService;
    private ImageStore imageStore;

    @Autowired
    public UserProfilePhotoController(UserServiceImpl userServiceImpl, ImageStoreInFileSystem imageStoreInFileSystem) {
        this.userService = userServiceImpl;
        this.imageStore = imageStoreInFileSystem;
    }

    @GetMapping
    public ResponseEntity<Resource> download(@PathVariable("id") String userId) throws Exception {
        User user = userService.findById(userId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageStore.getAsResource(user.getAvatar()));
    }

    @GetMapping(value = "/asBase64")
    public ResponseEntity<String> downloadAsBase64(@PathVariable("id") String userId) throws Exception {
        User user = userService.findById(userId);
        return ResponseEntity.ok(imageStore.getAsBase64String(user.getAvatar()));
    }

    @PostMapping
    public ResponseEntity<User> upload(@RequestParam("file") MultipartFile file,
                                       @PathVariable("id") String userId) throws IOException {
        return ResponseEntity.ok(userService.updateUserPhoto(userId, file));
    }
}
