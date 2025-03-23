package com.mrizkisaputra.services;

import com.mrizkisaputra.models.entities.RunningNumber;
import com.mrizkisaputra.repositories.RunningNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RunningNumberServiceImpl implements RunningNumberService {

    private final RunningNumberRepository runningNumberRepository;

    @Autowired
    public RunningNumberServiceImpl(RunningNumberRepository runningNumberRepository) {
        this.runningNumberRepository = runningNumberRepository;
    }

    @Override
    @Transactional
    public Long getNumber(String prefix) {
        RunningNumber rn = runningNumberRepository.findByPrefix(prefix);
        if (rn == null) {
            rn = new RunningNumber();
            rn.setPrefix(prefix);
        }

        rn.setLastNumber(rn.getLastNumber() + 1);
        runningNumberRepository.save(rn);

        return rn.getLastNumber();
    }

}
