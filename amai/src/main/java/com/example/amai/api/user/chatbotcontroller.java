package com.example.amai.api.user;

import com.example.amai.core.chat.entity.Chat;
import com.example.amai.core.chat.service.ChatService;
import com.example.amai.core.order.entity.Oder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/chat")
@CrossOrigin
public class chatbotcontroller {
    @Autowired
    private ChatService chatService;

    private final String PATH_URL_AI = "http://localhost:5000/";

    @GetMapping(value = "send/{msg}")
    public ResponseEntity<String> SendAPI(@PathVariable("msg") String msg) {
//        String uri = PATH_URL_AI + "send/" + msg;
//        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
        String result = "Xin chao quynh nhu";
        return result.equals("") ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Hiển thị tin nhắn của tài khoản đã đăng nhập (1 true : Đã xóa , 0 false: Tồn tại )
     *
     * @return
     */
    @GetMapping("{createBy}")
    public ResponseEntity<List<Chat>> findByIsDeletedAndAccount_IsDeletedAndAccount_UserName(@PathVariable("createBy") String createBy) {
        List<Chat> oderList = chatService.findByIsDeletedFalseAndCreateAt_UserName(createBy);
        return oderList.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(oderList, HttpStatus.OK);
    }
}