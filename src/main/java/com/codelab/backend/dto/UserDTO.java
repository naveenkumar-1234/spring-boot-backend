package com.codelab.backend.dto;

import com.codelab.backend.model.Users;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
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

    public Integer getSpr() {
        return spr;
    }

    public void setSpr(Integer spr) {
        this.spr = spr;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
