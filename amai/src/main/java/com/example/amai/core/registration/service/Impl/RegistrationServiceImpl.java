package com.example.amai.core.registration.service.Impl;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.registration.entity.Registration;
import com.example.amai.core.registration.repository.RegistrationRepository;
import com.example.amai.core.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public List<Registration> getAll() {
        return registrationRepository.findAll();
    }

    @Override
    public Optional<Registration> getById(Integer id) {
        return registrationRepository.findById(id);
    }

    @Override
    public Registration save(Registration entity) {
        return registrationRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        registrationRepository.deleteById(id);
    }
}
