package com.codelab.backend.repository;

import com.codelab.backend.model.Student;
import com.codelab.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUser(Users user);

    @Query("SELECT s.user FROM Student s WHERE :subjectCode MEMBER OF s.subjectCodes")
    List<Users> findStudentsBySubjectCode(String subjectCode);

    List<Student> findStudentsBySemester(Integer semester);
}
