package com.example.amai.core.order.entity.listener;

import com.example.amai.core.order.entity.Oder;
import com.example.amai.core.security.service.MyUserDetails;
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
 * Lớp thực hiện trước khi trương tác với table đặt món
 */
@Service
public class OrderListener implements EntityListeners {
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
     * Thực hiện trước khi thêm mới đặt món
     *
     * @param oder Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Oder oder) {
        oder.setCreateAt(LocalDateTime.now().format(formatter));
        oder.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        oder.setAccount(userRequest.getAccount());
        oder.setUpdatedBy(userRequest.getAccount());
        oder.setIsDeleted(false);
        oder.setPay(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại đặt món
     *
     * @param oder Thông tin đưa vào câu truy vấn
     */
    @PreRemove
    @PreUpdate
    public void preUpdate(Oder oder) {
        oder.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        oder.setUpdatedBy(userRequest.getAccount());
    }
}
