package com.example.amai.core.security.dto.user;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.admin_user.entity.contans.EGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Long id;
    private Account account;
    private String fullName;
    private LocalDate birthday;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private EGender gender;
    private String image;
}
