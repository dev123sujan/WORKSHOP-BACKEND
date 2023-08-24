package com.studyplan.app.repositories;

import com.studyplan.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//    Optional<User> findByUsernameOrEmail(String email, String userName);
    Boolean existsByEmail(String email);
//    Boolean existsByPassword(String password);

    User findByEmail(String email);
}
