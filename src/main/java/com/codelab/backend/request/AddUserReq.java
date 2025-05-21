package com.codelab.backend.request;

import com.codelab.backend.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

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


}
