package com.login.loginSecure.services;

import com.login.loginSecure.model.Image;

import java.util.List;

public class ImageServiceImpl implements ImageService{


    @Override
    public Image createImageForUser(String name, byte[] image) {
        return null;
    }

    @Override
    public Image updateImageForUser(Long id, String name, String username) {
        return null;
    }

    @Override
    public void deleteImageForUser(Long id, String username) {

    }

    @Override
    public List<Image> getImagesForUser(String username) {
        return List.of();
    }
}
