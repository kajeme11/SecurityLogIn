package com.login.loginSecure.services;

import com.login.loginSecure.model.Image;
import com.login.loginSecure.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImageServiceImpl implements ImageService{

    @Autowired
    ImageRepository imageRepository;

    @Override
    public Image createImageForUser(String username, String name, byte[] image) {
        Image img = new Image();
        img.setImage(image);
        img.setUsername(username);
        img.setName(name);
        Image savedImg = imageRepository.save(img);
        return savedImg;
    }

    @Override
    public Image updateImageForUser(Long id, String name, byte[] image) {
        Image img = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("error updating image"));
        img.setImage(image);
        img.setName(name);
        Image updatedImg = imageRepository.save(img);
        return updatedImg;
    }

    @Override
    public void deleteImageForUser(Long id, String username) {
        imageRepository.deleteById(id);
    }

    @Override
    public List<Image> getImagesForUser(String username) {
        List<Image> images = imageRepository.findByOwnerUsername(username);
        return images;
    }
}
