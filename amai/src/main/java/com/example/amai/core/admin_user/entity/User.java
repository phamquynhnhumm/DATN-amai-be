/**
 * Đồ Án Tốt Nghiệp NhuPTQ
 */
package com.example.amai.core.admin_user.entity;

import com.example.amai.core.admin_user.entity.contans.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
/**
 * Người dùng
 */ public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
     * Tên tài khoản
     * account là username (tên tài khoản) {{@link Account}}
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
    /**
     * Người tạo
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * Thời gian tạo
     */
    private LocalDate createAt;
    /**
     * Người cập nhật
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * Thời gian cập nhật
     */
    private LocalDate updateAt;

    /**
     * Cờ xóa
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
