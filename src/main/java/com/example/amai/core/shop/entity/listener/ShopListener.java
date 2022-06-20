package com.example.amai.core.shop.entity.listener;

import com.example.amai.core.security.service.MyUserDetails;
import com.example.amai.core.shop.entity.Shop;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ShopListener implements EntityListeners {
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
     * @param shop Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Shop shop) {
        shop.setUpdateAt(LocalDateTime.now().format(formatter));
        shop.setUpdatedBy(null);
    }

    /**
     * Thực hiện trước khi cập nhật lại chi tiết món
     *
     * @param shop Thông tin đưa vào câu truy vấn
     */
    @PreUpdate
    public void preUpdate(Shop shop) {
        shop.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        shop.setUpdatedBy(userRequest.getAccount());
    }
}
