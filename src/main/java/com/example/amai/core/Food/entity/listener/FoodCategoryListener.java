package com.example.amai.core.Food.entity.listener;

import com.example.amai.core.Food.entity.FoodCategory;
import com.example.amai.core.security.service.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lớp thực hiện trước khi trương tác với table danh mục
 */
@Service
public class FoodCategoryListener implements EntityListeners {
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
     * Thực hiện trước khi thêm mới danh mục
     *
     * @param foodCategory Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(FoodCategory foodCategory) {
        foodCategory.setCreateAt(LocalDateTime.now().format(formatter));
        foodCategory.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        foodCategory.setCreatedBy(userRequest.getAccount());
        foodCategory.setUpdatedBy(userRequest.getAccount());
        foodCategory.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại danh mục
     *
     * @param foodCategory Thông tin đưa vào câu truy vấn
     */
    @PreUpdate
    public void preUpdate(FoodCategory foodCategory) {
        foodCategory.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        foodCategory.setUpdatedBy(userRequest.getAccount());
    }
}
