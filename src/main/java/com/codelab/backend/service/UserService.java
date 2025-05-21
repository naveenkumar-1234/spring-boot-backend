package com.codelab.backend.service;

import com.codelab.backend.enums.UserRoles;
import com.codelab.backend.exceptions.AlreadyExistException;
import com.codelab.backend.exceptions.AuthException;
import com.codelab.backend.model.Staff;
import com.codelab.backend.model.Student;
import com.codelab.backend.model.Users;
import com.codelab.backend.repository.StaffRepository;
import com.codelab.backend.repository.StudentRepository;
import com.codelab.backend.repository.UsersRepository;
import com.codelab.backend.request.AddStaffReq;
import com.codelab.backend.request.AddStudentReq;
import com.codelab.backend.request.LoginApi;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;

    public Users registerStudent(AddStudentReq req) {
        if (usersRepository.existsByEmail(req.getEmail())) {
            throw new AlreadyExistException("Student id Already Exist");
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

}
