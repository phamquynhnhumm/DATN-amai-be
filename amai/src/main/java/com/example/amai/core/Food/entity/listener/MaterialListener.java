package com.example.amai.core.Food.entity.listener;

import com.example.amai.core.Food.entity.Material;
import org.springframework.stereotype.Service;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MaterialListener implements EntityListeners {
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
     * Thực hiện trước khi thêm mới nguyên liệu
     *
     * @param material Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Material material) {
        material.setCreateAt(LocalDateTime.now().format(formatter));
        material.setUpdateAt(LocalDateTime.now().format(formatter));
        material.setCreatedBy("quynhnhu");
        material.setUpdatedBy("thuthuy");
        material.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại nguyên liệu
     *
     * @param material Thông tin đưa vào câu truy vấn
     */
    @PreUpdate
    public void preUpdate(Material material) {
        material.setUpdateAt(LocalDateTime.now().format(formatter));
        material.setUpdatedBy("tientran");
    }
}

