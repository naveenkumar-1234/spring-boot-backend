package com.codelab.backend.repository;

import com.codelab.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    public Boolean existsByEmail(String email);

    public Users getByEmail(String email);
    Optional<Users> findByEmail(String email);
}
