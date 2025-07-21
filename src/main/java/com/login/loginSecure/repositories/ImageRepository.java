package com.login.loginSecure.repositories;

import com.login.loginSecure.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByUsername(String ownerUsername);

}
