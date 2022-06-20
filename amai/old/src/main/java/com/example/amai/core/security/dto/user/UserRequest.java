package com.example.amai.core.security.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Integer id;
    private String fullName;
    private AccountLoginDTO account;
    private String email;
}
