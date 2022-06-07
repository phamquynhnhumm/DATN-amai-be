/**
 * Đồ Án Tốt Nghiệp NhuPTQ
 */
package com.example.amai.core.admin_user.entity;

import com.example.amai.core.admin_user.entity.contans.EGender;
import com.example.amai.core.admin_user.entity.listener.UserListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EntityListeners(UserListener.class)
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
    @ManyToOne
    @JoinColumn(name = "created_by_user_name")
    private Account createdBy;

    /**
     * Thời gian tạo
     */
    private String createAt;
    /**
     * Người cập nhật
     */
    @ManyToOne
    @JoinColumn(name = "updated_by_user_name")
    private Account updatedBy;

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
     * Địa chỉ
     */
    private String address;

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
}
