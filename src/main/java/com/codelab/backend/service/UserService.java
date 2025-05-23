package com.codelab.backend.service;

import com.codelab.backend.config.JwtUtil;
import com.codelab.backend.enums.UserRoles;
import com.codelab.backend.exceptions.AlreadyExistException;
import com.codelab.backend.exceptions.AuthException;
import com.codelab.backend.exceptions.ResourceNotFoundException;
import com.codelab.backend.model.Staff;
import com.codelab.backend.model.Student;
import com.codelab.backend.model.Users;
import com.codelab.backend.repository.StaffRepository;
import com.codelab.backend.repository.StudentRepository;
import com.codelab.backend.repository.UsersRepository;
import com.codelab.backend.request.AddStaffReq;
import com.codelab.backend.request.AddStudentReq;
import com.codelab.backend.request.LoginApi;
import com.codelab.backend.response.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;
    private final JwtUtil jwtUtil;

    public Users registerStudent(AddStudentReq req) {
        if (usersRepository.existsByEmail(req.getEmail())) {
            throw new AlreadyExistException("Student email id Already Exist");
        } else if (usersRepository.existsBySpr(req.getSpr())) {
            throw new AlreadyExistException("SPR Already Exist");
        }
        else if (studentRepository.existsByRegisterNumber(req.getRegisterNumber())) {
            throw new AlreadyExistException("RegisterNumber Already Exist");
        }


        UserRoles roles = req.getUserRole();

        Users newUsers = new Users(
                req.getUsername(),
                req.getEmail(),
                hashPassword(req.getPassword()),
                req.getSpr(),
                roles
        );
        Users savedUser = usersRepository.save(newUsers);


            Student student = new Student();
            student.setUser(savedUser);
            student.setDepartment(req.getDepartment());
            student.setRegisterNumber(req.getRegisterNumber());
            student.setYear(req.getYear());
            student.setSemester(req.getSemester());
            System.out.println("---------------"+req.getSubjectCodes());
            student.setSubjectCodes(req.getSubjectCodes());

            studentRepository.save(student);



        return savedUser;
    }

    public Users registerStaff(AddStaffReq req) {
        if (usersRepository.existsByEmail(req.getEmail())) {
            throw new AlreadyExistException("Staff id Already Exist");
        }else if (usersRepository.existsBySpr(req.getSpr())) {
            throw new AlreadyExistException("SPR Already Exist");
        }

        Users newUsers = new Users(
                req.getUsername(),
                req.getEmail(),
                hashPassword(req.getPassword()),
                req.getSpr(),
                req.getUserRole()
        );
        Users savedUser = usersRepository.save(newUsers);

        Staff staff = new Staff();
        staff.setUser(savedUser);
        staff.setDepartment(req.getDepartment());
        staff.setSubjectCodes(req.getSubjectCodes());

        staffRepository.save(staff);

        return savedUser;
    }

    public ResponseEntity<ApiResponse> getSubjectByUser(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse("Token not found", null));
        }
        String email = jwtUtil.getEmailFromToken(token);
        Optional<Users> optionalUser = usersRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse( "User not found", null));
        }

        Users user = optionalUser.get();
        if (user.getUserRole() == UserRoles.STAFF) {
            Staff staff = staffRepository.findByUser(user);
            return ResponseEntity.ok(new ApiResponse("Subjects fetched", staff.getSubjectCodes()));
        } else if (user.getUserRole() == UserRoles.STUDENT) {
            Student student = studentRepository.findByUser(user);
            return ResponseEntity.ok(new ApiResponse( "Subjects fetched", student.getSubjectCodes()));
        } else {
            return ResponseEntity.ok(new ApiResponse("User has no subject codes", null));
        }
    }


    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public Users loginUser(LoginApi req) {
        Optional<Users> optionalUser = usersRepository.findByEmail(req.getEmail());

        Users user = optionalUser.orElseThrow(() -> new AuthException("User not found with email: " + req.getEmail()));

        if (!BCrypt.checkpw(req.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid Password");
        }

        return user;
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));
    }

}
