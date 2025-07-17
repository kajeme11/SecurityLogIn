package com.login.loginSecure.services;

import com.login.loginSecure.model.Image;

import java.util.List;

public interface ImageService {
    Image createImageForUser(String name, byte[] image);
    Image updateImageForUser(Long id, String name, String username);
    void deleteImageForUser(Long id, String username);
    List<Image> getImagesForUser(String username);
}
