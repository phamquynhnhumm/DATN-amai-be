package com.example.amai.core.order.entity.listener;

import com.example.amai.core.order.entity.OrderDetail;
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
 * Lớp thực hiện trước khi trương tác với table chi tiết đặt món
 */
@Service
public class OrderDetailListener implements EntityListeners {
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
     * @param orderDetail Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(OrderDetail orderDetail) {
        orderDetail.setCreateAt(LocalDateTime.now().format(formatter));
        orderDetail.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderDetail.setCreatedBy(userRequest.getAccount());
        orderDetail.setUpdatedBy(userRequest.getAccount());
        orderDetail.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại chi tiết đặt món
     *
     * @param orderDetail Thông tin đưa vào câu truy vấn
     */
    @PreRemove
    @PreUpdate
    public void preUpdate(OrderDetail orderDetail) {
        orderDetail.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderDetail.setUpdatedBy(userRequest.getAccount());
    }
}

