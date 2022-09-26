package com.macOfBa.service.ipmlSer;

import com.macOfBa.model.Motor;
import com.macOfBa.repository.IMotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MotorService implements IMotorService {
    @Autowired
    private IMotorRepository motorRepository;

    @Override
    public Iterable<Motor> findAll() {
        return motorRepository.findAll();
    }

    @Override
    public Optional<Motor> findById(Long id) {
        return motorRepository.findById(id);
    }

    @Override
    public void save(Motor motor) {
        motorRepository.save(motor);
    }

    @Override
    public void remove(Long id) {
        motorRepository.deleteById(id);
    }
}