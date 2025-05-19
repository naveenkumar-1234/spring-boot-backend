package com.codelab.backend.request;

import com.codelab.backend.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



public class AddUserReq {
    private String username;
    private String email;
    private String password;
    private Integer spr;
    private UserRoles userRole;

    public AddUserReq(String username, String email, String password, Integer spr, UserRoles userRole) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.spr = spr;
        this.userRole = userRole;
    }

    public AddUserReq() {
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
