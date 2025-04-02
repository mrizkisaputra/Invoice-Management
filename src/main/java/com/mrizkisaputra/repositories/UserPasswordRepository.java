package com.mrizkisaputra.repositories;

import com.mrizkisaputra.models.entities.User;
import com.mrizkisaputra.models.entities.UserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPasswordRepository extends JpaRepository<UserPassword, String> {
    UserPassword user(User user);

    UserPassword findByUser(User user);
}
