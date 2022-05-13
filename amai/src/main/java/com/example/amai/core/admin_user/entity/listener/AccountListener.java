package com.example.amai.core.admin_user.entity.listener;

import com.example.amai.core.admin_user.entity.Account;
import org.springframework.stereotype.Service;

import javax.persistence.EntityListeners;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class AccountListener  implements EntityListeners {
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
     * Thực hiện trước khi thêm mới tài khoản
     *
     * @param account Thông tin đưa vào câu truy vấn
     */
    @PrePersist
    public void preInser(Account account) {
        account.setCreateAt(LocalDateTime.now().format(formatter));
        account.setUpdateAt(LocalDateTime.now().format(formatter));
        account.setCreatedBy("quynhnhu");
        account.setUpdatedBy("thuthuy");
        account.setIsDeleted(false);
    }

    /**
     * Thực hiện trước khi cập nhật lại tài khoản
     *
     * @param account Thông tin đưa vào câu truy vấn
     */
    @PreRemove
    @PreUpdate
    public void preUpdate(Account account) {
        account.setUpdateAt(LocalDateTime.now().format(formatter));
        account.setUpdatedBy("tientran");
    }
}
