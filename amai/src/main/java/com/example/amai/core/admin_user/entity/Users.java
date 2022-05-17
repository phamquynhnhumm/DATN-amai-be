/**
 * Đồ Án Tốt Nghiệp NhuPTQ
 */
package com.example.amai.core.admin_user.entity;

import com.example.amai.core.admin_user.entity.contans.EGender;
import com.example.amai.core.admin_user.entity.contans.Provider;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

//@EntityListeners(UserListener.class)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Người tạo
     */
    private String createdBy;

    /**
     * Thời gian tạo
     */
    private String createAt;
    /**
     * Người cập nhật
     */
    private String updatedBy;

    /**
     * Thời gian cập nhật
     */
    private String updateAt;

    /**
     * Cờ xóa
     */
    private Boolean isDeleted;

    /**
     * Họ tên
     */
    private String fullName;

    /**
     * Ngày sinh
     */
    private LocalDate birthday;

    /**
     * Email
     */
    private String email;

    /**
     * Số điện thoại
     */
    private String phone;

    /**
     * Giới tính người dùng {@link EGender}
     */
    @Enumerated(EnumType.STRING)
    private EGender gender;

    /**
     * Ảnh người dùng
     */
    private String image;

    /**
     * Xác nhận loại tài khoản gg, facebook hay tài khoản đăng nhập
     */
    @Enumerated(EnumType.STRING)
    private Provider provider;

    /**
     * Tên tài khoản
     * account là username (tên tài khoản) {{@link Account}}
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
}
