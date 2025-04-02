package com.mrizkisaputra.repositories;

import com.mrizkisaputra.models.entities.ResetPassword;
import com.mrizkisaputra.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetPasswordRepository extends JpaRepository<ResetPassword, String> {
    Optional<ResetPassword> findByUniqueCode(String uniqueCode);

    void deleteByUser(User user);
}
