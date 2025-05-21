package com.codelab.backend.repository;

import com.codelab.backend.model.Staff;
import com.codelab.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff,Long> {
    Staff findByUser(Users user);
}
