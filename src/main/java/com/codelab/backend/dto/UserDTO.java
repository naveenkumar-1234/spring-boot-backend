package com.codelab.backend.dto;

import com.codelab.backend.model.Users;

public class UserDTO {
    private String username;
    private String email;
    private Integer spr;
    private String userRole;

    public UserDTO(Users user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.spr = user.getSpr();
        this.userRole = user.getUserRole().toString();
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Integer getSpr() {
        return spr;
    }

    public String getUserRole() {
        return userRole;
    }
}
