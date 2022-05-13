package com.example.amai.core.admin_user.entity.listener;

import com.example.amai.core.admin_user.entity.Users;
import org.springframework.stereotype.Service;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lớp thực hiện trước khi trương tác với table người dùng
 */
@Service
public class UserListener implements EntityListeners {
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
     * Thực hiện trước khi thêm mới người dùng
     *
     * @param users Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Users users) {
        users.setCreateAt(LocalDateTime.now().format(formatter));
        users.setUpdateAt(LocalDateTime.now().format(formatter));
        users.setCreatedBy("quynhnhu");
        users.setUpdatedBy("thuthuy");
        users.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại người dùng
     *
     * @param users Thông tin đưa vào câu truy vấn
     */
    @PreRemove
    @PreUpdate
    public void preUpdate(Users users) {
        users.setUpdateAt(LocalDateTime.now().format(formatter));
        users.setUpdatedBy("tientran");
    }
}
