package com.login.loginSecure.services;

import com.login.loginSecure.dto.UserDTO;
import com.login.loginSecure.model.User;

import java.util.List;

public interface UserService {
    void updateUserRole(Long userId, String roleName);
    List<User> getAllUsers();
    UserDTO getUserById(Long id);
}
