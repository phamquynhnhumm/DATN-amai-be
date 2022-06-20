package com.example.amai.api.user;

import com.example.amai.core.Food.entity.Food;
import com.example.amai.core.chat.entity.Chat;
import com.example.amai.core.chat.service.ChatService;
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

    @PostMapping("send")
    public ResponseEntity<Chat> SendAPI(@RequestBody Chat chat) {
        String uri = PATH_URL_AI + "send/" + chat.getMsg();
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        chat.setMsgchatbot(result);
        if (chat.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(chatService.save(chat));
    }

    /**
     * Thu hồi tin nhắn
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Chat> deleteCartShopping(@PathVariable("id") Integer id) {
        Chat chat = chatService.getById(id).orElse(null);
        if (chat.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            chatService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    /**
     * Xóa chỉ mình tôi
     *
     * @return
     */
    @PutMapping("cancel")
    public ResponseEntity<Chat> undeleteFood(@RequestBody Chat chats) {
        Chat chat = chatService.getById(chats.getId()).orElse(null);
        if (chat.equals(null)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            chat.setMsg("Tin nhắn đã được thu hồi");
            chatService.save(chat);
            return new ResponseEntity<>(HttpStatus.OK);
        }
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

    @GetMapping("findbyid/{id}")
    public ResponseEntity<Chat> findById(@PathVariable("id") Integer id) {
        Chat chat = chatService.getById(id).orElse(null);
        return chat.equals(null) ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(chat, HttpStatus.OK);
    }
}