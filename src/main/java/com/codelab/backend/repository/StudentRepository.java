package com.codelab.backend.repository;

import com.codelab.backend.model.Student;
import com.codelab.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByUser(Users user);
}
