package com.example.amai.core.chat.entity.listener;

import com.example.amai.core.chat.entity.Chat;
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
public class ChatListener implements EntityListeners {
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
     * @param chat Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Chat chat) {
        chat.setCreateAt(LocalDateTime.now().format(formatter));
        chat.setUpdateAt(LocalDateTime.now().format(formatter));
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        chat.setCreatedBy(userRequest.getAccount());
        chat.setUpdatedBy("Tiệm trà sữa AMAI");
        chat.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại món
     *
     * @param chat Thông tin đưa vào câu truy vấn
     */
    @PreUpdate
    public void preUpdate(Chat chat) {
        chat.setUpdateAt(LocalDateTime.now().format(formatter));
        chat.setUpdatedBy("Tiệm trà sữa AMAI");
    }
}

