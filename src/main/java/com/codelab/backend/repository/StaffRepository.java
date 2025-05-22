package com.codelab.backend.repository;

import com.codelab.backend.model.Staff;
import com.codelab.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findByUser(Users user);
    @Query("SELECT s.user FROM Staff s WHERE :subjectCode MEMBER OF s.subjectCodes")
    List<Users> findStaffBySubjectCode(String subjectCode);

}
