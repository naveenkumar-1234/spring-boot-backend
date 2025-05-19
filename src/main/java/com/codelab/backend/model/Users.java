package com.codelab.backend.model;

import com.codelab.backend.enums.UserRoles;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Integer spr;
    @NonNull
    @Enumerated(EnumType.STRING)
    private UserRoles userRole;

    public Users() {
    }

    public Users(@NonNull String username, @NonNull String email, @NonNull String password, @NonNull Integer spr, @NonNull UserRoles userRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.spr = spr;
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSpr() {
        return spr;
    }

    public void setSpr(Integer spr) {
        this.spr = spr;
    }

    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }
}
