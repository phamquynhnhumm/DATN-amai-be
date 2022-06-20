package com.example.amai.core.order.entity.listener;

import com.example.amai.core.order.entity.Cart;
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

@Service
public class CartListener implements EntityListeners {
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
     * Thực hiện trước khi thêm mới chi tiết đặt món
     *
     * @param cart Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Cart cart) {
        cart.setCreateAt(LocalDateTime.now().format(formatter));
        cart.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cart.setCreatedBy(userRequest.getAccount());
        cart.setUpdatedBy(userRequest.getAccount());
        cart.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại chi tiết đặt món
     *
     * @param cart Thông tin đưa vào câu truy vấn
     */
    @PreRemove
    @PreUpdate
    public void preUpdate(Cart cart) {
        cart.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cart.setUpdatedBy(userRequest.getAccount());
    }
}
