package com.example.amai.core.registration.entity;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.registration.entity.contans.EStatuasHandle;
import com.example.amai.core.registration.entity.listener.RegistrationListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@EntityListeners(RegistrationListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    /**
     * ID danh mục
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Thời gian đăng ký
     */
    private String createAt;

    /**
     * Thời gian cập nhật
     */
    private String updateAt;

    /**
     * Người cập nhật
     */
    @ManyToOne
    @JoinColumn(name = "updated_by_user_name")
    private Account updatedBy;

    /**
     * Cờ xóa
     */
    private Boolean isDeleted;

    /**
     * Trạng thái xử lý
     */
    @Enumerated(EnumType.STRING)
    private EStatuasHandle handle;

    /**
     * Họ tên người đăng ký
     */
    private String name;

    /**
     * Số điên thoại đăng ký
     */
    private String phone;

    /**
     * Yêu cầu của người đăng ký
     */
    private String content;

}
