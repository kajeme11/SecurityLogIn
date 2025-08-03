package com.login.loginSecure.services;

import com.login.loginSecure.model.AppRole;
import com.login.loginSecure.model.Role;
import com.login.loginSecure.model.User;
import com.login.loginSecure.repositories.RoleRepository;
import com.login.loginSecure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void updateUserRole(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Error finding user by Id"));
        AppRole appRole = AppRole.valueOf(roleName);
        Role role = roleRepository.findByRoleName(appRole).orElseThrow(() -> new RuntimeException("Error getting role name"));
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return null;
    }
}
