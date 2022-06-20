package com.example.amai.core.registration.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.registration.entity.Registration;
import com.example.amai.core.registration.entity.contans.EStatuasHandle;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegistrationService extends IService<Registration, Integer> {
    List<Registration> findAllByIsDeletedFalseAndHandle(EStatuasHandle handle);

    List<Registration> findAllByIsDeleted(boolean idDelete);

    List<Registration> findSearch(boolean isDelete,String name, String phone);

}
