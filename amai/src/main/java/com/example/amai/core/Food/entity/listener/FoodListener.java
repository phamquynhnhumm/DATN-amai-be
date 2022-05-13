package com.example.amai.core.Food.entity.listener;

import com.example.amai.core.Food.entity.Food;
import org.springframework.stereotype.Service;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lớp thực hiện trước khi trương tác với table món
 */
@Service
public class FoodListener implements EntityListeners {
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
     * Thực hiện trước khi thêm mới món
     *
     * @param food Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Food food) {
        food.setCreateAt(LocalDateTime.now().format(formatter));
        food.setUpdateAt(LocalDateTime.now().format(formatter));
        food.setCreatedBy("quynhnhu");
        food.setUpdatedBy("thuthuy");
        food.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại món
     *
     * @param food Thông tin đưa vào câu truy vấn
     */
    @PreUpdate
    public void preUpdate(Food food) {
        food.setUpdateAt(LocalDateTime.now().format(formatter));
        food.setUpdatedBy("tientran");
    }
}
