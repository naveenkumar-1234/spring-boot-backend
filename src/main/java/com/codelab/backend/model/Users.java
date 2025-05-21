package com.codelab.backend.model;

import com.codelab.backend.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(name = "username", nullable = false)
    private String username;

    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull(message = "SPR is required")
    @Column(name = "spr", nullable = false, unique = true)
    private Integer spr;

    @NotNull(message = "User role is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRoles userRole;

    // Custom constructor without id (for creating new Users)
    public Users(String username, String email, String password, Integer spr, UserRoles userRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.spr = spr;
        this.userRole = userRole;
    }
}
