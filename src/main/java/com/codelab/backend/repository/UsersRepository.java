package com.codelab.backend.repository;

import com.codelab.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    static void findUserById(Long id) {
    }

    public Boolean existsByEmail(String email);

    public Users getByEmail(String email);
    Optional<Users> findByEmail(String email);

    boolean existsBySpr(Integer spr);
}
