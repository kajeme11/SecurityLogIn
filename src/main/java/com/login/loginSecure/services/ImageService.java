package com.login.loginSecure.services;

import com.login.loginSecure.model.Image;

import java.util.List;

public interface ImageService {
    Image createImageForUser(String username, String name, byte[] image);
    Image updateImageForUser(Long id, String name, byte[] image);
    void deleteImageForUser(Long id, String username);
    List<Image> getImagesForUser(String username);
}
