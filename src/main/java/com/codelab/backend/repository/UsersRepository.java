package com.codelab.backend.repository;

import com.codelab.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    public Boolean existsByEmail(String email);
    public Users findByEmail(String email);
    public Users getByEmail(String email);
}
