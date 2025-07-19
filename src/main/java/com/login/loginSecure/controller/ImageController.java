package com.login.loginSecure.controller;

import com.login.loginSecure.model.Image;
import com.login.loginSecure.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping
    public Image createImage(@RequestBody byte[] image,
                             @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        System.out.println("Username: " + username);
        return imageService.createImageForUser(username, username, image);
    }

    @GetMapping
    public List<Image> getImages(@AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        System.out.println("Getting " + username + "'s images");
        return imageService.getImagesForUser(username);
    }

    @PutMapping("/{imageId}")
    public Image updateImage(@PathVariable Long imageId,
                             @RequestBody byte[] image,
                             @AuthenticationPrincipal UserDetails userDetails
                             ){
        String username = userDetails.getUsername();
        return imageService.updateImageForUser(imageId, username, image);
    }

    @DeleteMapping("/{imageId}")
    public void deleteImage(@PathVariable long imageId,
                            @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        imageService.deleteImageForUser(imageId, username);
    }

}
