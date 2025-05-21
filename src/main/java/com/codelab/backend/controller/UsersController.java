package com.codelab.backend.controller;

import com.codelab.backend.config.JwtUtil;
import com.codelab.backend.dto.UserDTO;
import com.codelab.backend.model.Users;
import com.codelab.backend.request.AddStaffReq;
import com.codelab.backend.request.AddStudentReq;
import com.codelab.backend.request.AddUserReq;
import com.codelab.backend.request.LoginApi;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final JwtUtil jwtUtil;


//    @CrossOrigin(origins = )
//    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid  @RequestBody LoginApi req, HttpServletResponse response) {
        try {

            UserDTO userDTO = new UserDTO(userService.loginUser(req));
            String token = jwtUtil.generateToken(userDTO.getEmail(),userDTO.getUserRole());
            System.out.println(token);
            Cookie cookie = new Cookie("jwt_token", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("Login Successful", userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
