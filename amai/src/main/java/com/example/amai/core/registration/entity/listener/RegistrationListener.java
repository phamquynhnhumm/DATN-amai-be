package com.example.amai.core.registration.entity.listener;

import com.example.amai.core.registration.entity.Registration;
import com.example.amai.core.registration.entity.contans.EStatuasHandle;
import com.example.amai.core.security.service.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RegistrationListener implements EntityListeners {
    @Override
    public Class[] value() {
        return new Class[0];
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }

    /**
     * format ngày giờ
     */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    /**
     * Thực hiện trước khi thêm mới chi tiết món
     *
     * @param registration Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Registration registration) {
        registration.setCreateAt(LocalDateTime.now().format(formatter));
        registration.setUpdateAt(LocalDateTime.now().format(formatter));
        registration.setUpdatedBy(null);
        registration.setHandle(EStatuasHandle.NOTCONTACTED);
        registration.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại chi tiết món
     *
     * @param registration Thông tin đưa vào câu truy vấn
     */
    @PreUpdate
    public void preUpdate(Registration registration) {
        registration.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        registration.setUpdatedBy(userRequest.getAccount());
    }
}

