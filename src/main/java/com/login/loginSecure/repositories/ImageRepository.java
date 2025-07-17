package com.login.loginSecure.repositories;

import com.login.loginSecure.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByOwnerUsername(String ownerUsername);

}
