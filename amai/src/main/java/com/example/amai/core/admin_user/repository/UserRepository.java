package com.example.amai.core.admin_user.repository;

import com.example.amai.core.admin_user.entity.Users;
import com.example.amai.core.admin_user.entity.contans.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Boolean existsByEmail(String email);

    Boolean existsByPhone(String phone);

    @Query(value = "select email from users where account_user_name = :userName", nativeQuery = true)
    String findAllByEmail(@Param("userName") String userName);

    Optional<Users> findByEmail(String email);

    Users findByAccount_UserName(String username);

    List<Users> findUserByIsDeletedFalse();

    List<Users> findAllByAccount_Role(ERole role);

    @Query(value = "select  * from users as us inner join account as ac on ac.user_name = us.account_user_name " +
            "where ac.role = 'ROLE_CUSTOMER' and us.is_deleted ='false' and ac.is_deleted = 'false' " +
            "and us.full_name like %:fullName% " +
            "and us.account_user_name like  %:userName%  " +
            "and us.phone like %:phone%  " +
            "and us.email like  %:email% " +
            "and us.address like  %:address% ", nativeQuery = true)
    List<Users> search(@Param("fullName") String fullName,
                       @Param("userName") String userName,
                       @Param("phone") String phone,
                       @Param("email") String email,
                       @Param("address") String address);
}
