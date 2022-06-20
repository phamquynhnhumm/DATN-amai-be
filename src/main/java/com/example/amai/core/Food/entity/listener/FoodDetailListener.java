package com.example.amai.core.Food.entity.listener;

import com.example.amai.core.Food.entity.FoodDetail;
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
public class FoodDetailListener implements EntityListeners {
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
     * @param foodDetail Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(FoodDetail foodDetail) {
        foodDetail.setCreateAt(LocalDateTime.now().format(formatter));
        foodDetail.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        foodDetail.setCreatedBy(userRequest.getAccount());
        foodDetail.setUpdatedBy(userRequest.getAccount());
        foodDetail.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại chi tiết món
     *
     * @param foodDetail Thông tin đưa vào câu truy vấn
     */
    @PreUpdate
    public void preUpdate(FoodDetail foodDetail) {
        foodDetail.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        foodDetail.setUpdatedBy(userRequest.getAccount());
    }
}

