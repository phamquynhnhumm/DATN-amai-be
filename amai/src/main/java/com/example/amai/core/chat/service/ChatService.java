package com.example.amai.core.chat.service;

import com.example.amai.core.Food.service.IService;
import com.example.amai.core.chat.entity.Chat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatService extends IService<Chat, Integer> {
    List<Chat> findByIsDeletedFalseAndCreateAt_UserName(String createAt);
}
