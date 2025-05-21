package com.codelab.backend.config;

import com.codelab.backend.enums.UserRoles;
import com.codelab.backend.model.Staff;
import com.codelab.backend.model.Student;
import com.codelab.backend.model.Users;
import com.codelab.backend.repository.StaffRepository;
import com.codelab.backend.repository.StudentRepository;
import com.codelab.backend.repository.UsersRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupDataLoader implements CommandLineRunner {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {

            Users admin = new Users();
            admin.setUsername("admin");
            admin.setEmail("admin@gamil.com");
            admin.setPassword(BCrypt.hashpw("admin@123", BCrypt.gensalt()));
            admin.setSpr(0);
            admin.setUserRole(UserRoles.ADMIN);
            userRepository.save(admin);
            System.out.println("Admin user saved at startup.");


            Users staffUser = new Users();
            staffUser.setUsername("staff1");
            staffUser.setEmail("staff1@gmail.com");
            staffUser.setPassword(BCrypt.hashpw("staff@123", BCrypt.gensalt()));
            staffUser.setSpr(1);
            staffUser.setUserRole(UserRoles.STAFF);
            userRepository.save(staffUser);

            Staff staff = new Staff();
            staff.setDepartment("Computer Science");
            staff.setSubjectCodes(List.of("CS101", "CS102"));
            staff.setUser(staffUser);
            staffRepository.save(staff);
            System.out.println("Staff user and entity saved at startup.");


            Users studentUser = new Users();
            studentUser.setUsername("student1");
            studentUser.setEmail("student1@gmail.com");
            studentUser.setPassword(BCrypt.hashpw("student@123", BCrypt.gensalt()));
            studentUser.setSpr(2);
            studentUser.setUserRole(UserRoles.STUDENT);
            userRepository.save(studentUser);

            Student student = new Student();
            student.setDepartment("Computer Science");
            student.setRegisterNumber("REG2023001");
            student.setYear(3);
            student.setSemester(6);
            student.setSubjectCodes(List.of("CS101", "CS103"));
            student.setUser(studentUser);
            studentRepository.save(student);
            System.out.println("Student user and entity saved at startup.");
        }
}

