package com.example.amai.core.suppliner.entity.listener;

import com.example.amai.core.security.service.MyUserDetails;
import com.example.amai.core.suppliner.entity.Supplier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lớp thực hiện trước khi trương tác với table nhà cung cấp
 */
@Service
public class SupplierListener implements EntityListeners {
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
     * Thực hiện trước khi thêm mới nhà cung cấp
     *
     * @param supplier Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Supplier supplier) {
        supplier.setCreateAt(LocalDateTime.now().format(formatter));
        supplier.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        supplier.setCreatedBy(userRequest.getAccount());
        supplier.setUpdatedBy(userRequest.getAccount());
        supplier.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật nhà cung cấp
     *
     * @param supplier Thông tin đưa vào câu truy vấn
     */
    @PreRemove
    @PreUpdate
    public void preUpdate(Supplier supplier) {
        supplier.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        supplier.setUpdatedBy(userRequest.getAccount());
    }
}
