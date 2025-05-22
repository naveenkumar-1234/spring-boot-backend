package com.codelab.backend.controller;

import com.codelab.backend.config.JwtUtil;
import com.codelab.backend.dto.UserDTO;
import com.codelab.backend.exceptions.ResourceNotFoundException;
import com.codelab.backend.model.Users;
import com.codelab.backend.repository.StaffRepository;
import com.codelab.backend.repository.StudentRepository;
import com.codelab.backend.request.AddStaffReq;
import com.codelab.backend.request.AddStudentReq;
import com.codelab.backend.request.AddUserReq;
import com.codelab.backend.request.LoginApi;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UsersController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;


//    @CrossOrigin(origins = )
//    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/auth/login")
    public ResponseEntity<ApiResponse> login(@Valid  @RequestBody LoginApi req, HttpServletResponse response) {
        try {

            UserDTO userDTO = new UserDTO(userService.loginUser(req));
            String token = jwtUtil.generateToken(userDTO.getEmail(),userDTO.getUserRole());
            System.out.println(token);
            Cookie cookie = new Cookie("token", token);
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

    @GetMapping("/user/getallusers")
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            List<Users> users = userService.getAllUsers();

            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponse("User Not Found", null));
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ApiResponse("Success", users));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error", null));
        }
    }
    @GetMapping("/user/id")
    public ResponseEntity<ApiResponse> getUsersById(@RequestBody Long id) {
        try {
            Users users = userService.getUserById(id);


                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ApiResponse("Success", users));


        }
        catch (ResourceNotFoundException e){
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/students/subject-code/{subjectCode}")
    public ResponseEntity<ApiResponse> getAllStudentsBySubjectCode(@PathVariable String subjectCode) {
        try {
            System.out.println("Subject code: " + subjectCode);
            List<Users> students = studentRepository.findStudentsBySubjectCode(subjectCode);

            if (students.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT)
                        .body(new ApiResponse("No students found for subject code: " + subjectCode, null));
            }

            return ResponseEntity.ok(new ApiResponse("Students fetched successfully", students));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error: " + e.getMessage(), null));
        }
    }
    @GetMapping("/staff/subject-code")
    public ResponseEntity<ApiResponse> getAllStaffBySubjectCode(@RequestBody String subjectCode) {
        try {
            List<Users> staff = staffRepository.findStaffBySubjectCode(subjectCode);
            if (staff.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No staff found for this subject", null));
            }
            return ResponseEntity.ok(new ApiResponse("Success", staff));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }


    @GetMapping("/user/subject-codes")
    public ResponseEntity<ApiResponse> getSubjectCode(HttpServletRequest request) {
        return userService.getSubjectByUser(request);
    }
}
