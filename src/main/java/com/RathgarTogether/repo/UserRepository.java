package com.RathgarTogether.repo;

import com.RathgarTogether.entities.User;
import com.RathgarTogether.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByEmail(String email);

    User findByRole(UserRole role);
    List<User> findAllByRole(UserRole role);

    Optional<User> findByEmail(String email);

}
