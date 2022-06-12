package com.example.amai.core.admin_user.entity.listener;

import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.entity.contans.EGender;
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
        users.setUpdateAt(LocalDateTime.now().format(formatter));
        users.setCreateAt(LocalDateTime.now().format(formatter));
        users.setBirthday(null);
        users.setAddress(null);
        users.setImage(null);
        users.setGender(EGender.FEMALE);
        users.setIsDeleted(false);
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            users.setCreatedBy(null);
            users.setUpdatedBy(null);
        } else {
            MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            users.setCreatedBy(userRequest.getAccount());
            users.setUpdatedBy(userRequest.getAccount());
            users.setIsDeleted(false);

        }
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
        MyUserDetails userRequest = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        users.setUpdatedBy(userRequest.getAccount());
    }
}
