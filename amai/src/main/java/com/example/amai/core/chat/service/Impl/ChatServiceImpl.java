package com.example.amai.core.chat.service.Impl;

import com.example.amai.core.chat.entity.Chat;
import com.example.amai.core.chat.repository.ChatRepository;
import com.example.amai.core.chat.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.PrimitiveIterator;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @Override
    public Optional<Chat> getById(Integer id) {
        return chatRepository.findById(id);
    }

    @Override
    public Chat save(Chat entity) {
        return chatRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        this.chatRepository.deleteById(id);
    }

    @Override
    public List<Chat> findByIsDeletedFalseAndCreateAt_UserName(String createAt) {
        return chatRepository.findByIsDeletedFalseAndCreateAt_UserName(createAt);
    }
}
