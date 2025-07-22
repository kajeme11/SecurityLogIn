package com.login.loginSecure.repositories;

import com.login.loginSecure.model.AppRole;
import com.login.loginSecure.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole role);
}
