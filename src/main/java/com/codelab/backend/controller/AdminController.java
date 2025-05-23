package com.codelab.backend.controller;

import com.codelab.backend.config.JwtUtil;
import com.codelab.backend.dto.UserDTO;
import com.codelab.backend.model.Staff;
import com.codelab.backend.model.Student;
import com.codelab.backend.model.Users;
import com.codelab.backend.repository.StaffRepository;
import com.codelab.backend.repository.StudentRepository;
import com.codelab.backend.request.AddStaffReq;
import com.codelab.backend.request.AddStudentReq;
import com.codelab.backend.response.ApiResponse;
import com.codelab.backend.service.StaffService;
import com.codelab.backend.service.StudentService;
import com.codelab.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")

public class AdminController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final StaffService staffService;
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;

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

    @GetMapping("/getallusers")
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
    @GetMapping("/getallstaff")
    public ResponseEntity<List<Staff>> getAllStaff() {
        return ResponseEntity.ok(staffService.getAllStaffs());
    }

    @GetMapping("/getallstudent")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students=studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/students/subject-code/{subjectCode}")
    public ResponseEntity<ApiResponse> getAllStudentsBySubjectCode(@PathVariable String subjectCode) {
        try {
            System.out.println("Subject code: " + subjectCode);
            List<Users> students = studentRepository.findStudentsBySubjectCode(subjectCode);

            if (students.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No students found for subject code: " + subjectCode, null));
            }

            return ResponseEntity.ok(new ApiResponse("Students fetched successfully", students));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error: " + e.getMessage(), null));
        }
    }

    @GetMapping("/staff/subject-code/{subjectCode}")
    public ResponseEntity<ApiResponse> getAllStaffBySubjectCode(@PathVariable String subjectCode) {
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
    @GetMapping("/students/semester/{semester}")
    public ResponseEntity<ApiResponse> getAllStudentsBySemester(@PathVariable Integer semester) {
        try {
            List<Student> students = studentRepository.findStudentsBySemester(semester);

            if (students.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("No students found for semester: " + semester, null));
            }

            return ResponseEntity.ok(new ApiResponse("Students fetched successfully", students));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error: " + e.getMessage(), null));
        }
    }


}
