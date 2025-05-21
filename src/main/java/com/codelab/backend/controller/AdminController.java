package com.codelab.backend.controller;

import com.codelab.backend.config.JwtUtil;
import com.codelab.backend.dto.UserDTO;
import com.codelab.backend.request.AddStaffReq;
import com.codelab.backend.request.AddStudentReq;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")

public class AdminController {
    private final UserService userService;
    private final JwtUtil jwtUtil;


    @PostMapping("/staff/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody AddStaffReq req) {
        try {

            UserDTO userDTO = new UserDTO(userService.registerStaff(req));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Staff Registered Successfully", userDTO));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PostMapping("/student/register")
    public ResponseEntity<ApiResponse> registerStudent(@Valid @RequestBody AddStudentReq req) {
        try {

            UserDTO userDTO = new UserDTO(userService.registerStudent(req));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Student Registered Successfully", userDTO));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
