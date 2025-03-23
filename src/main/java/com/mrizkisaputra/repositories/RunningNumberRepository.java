package com.mrizkisaputra.repositories;

import com.mrizkisaputra.models.entities.RunningNumber;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface RunningNumberRepository extends JpaRepository<RunningNumber, String> {

    /**
     * select * from running_number where prefix = ?
     */
    @Lock(LockModeType.PESSIMISTIC_READ)
    RunningNumber findByPrefix(String prefix);

}
