package com.login.loginSecure.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @NotNull
    @Email
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @Size(max = 120)
    @JsonIgnore
    @Column(name = "password")
    private String password;

    private boolean accountNonLocked = true;
    private boolean accountNotExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;

    private String twoFactorSecrete;
    private boolean isTwoFactorEnabled;
    private String signUpMethod;

    @ManyToOne(fetch =  FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name="role_id", referencedColumnName = "role_id")
    @JsonBackReference
    @ToString.Exclude
    private Role role;

}
