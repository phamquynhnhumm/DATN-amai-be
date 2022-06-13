package com.example.amai.core.chat.repository;

import com.example.amai.core.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findByIsDeletedFalseAndCreateAt_UserName(String createAt);
}
