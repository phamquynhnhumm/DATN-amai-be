package com.example.amai.core.registration.repository;

import com.example.amai.core.registration.entity.Registration;
import com.example.amai.core.registration.entity.contans.EStatuasHandle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {
    List<Registration> findAllByIsDeletedFalseAndHandle(EStatuasHandle handle);

    List<Registration> findAllByIsDeleted(boolean idDelete);

    @Query(value = "select * from registration as s where s.is_deleted = :isDelete and s.name like %:name%  and s.phone like %:phone% ", nativeQuery = true)
    List<Registration> findSearch(@Param("isDelete") boolean isDelete, @Param("name") String name, @Param("phone") String phone);
}
