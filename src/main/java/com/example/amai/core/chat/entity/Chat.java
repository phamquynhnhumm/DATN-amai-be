package com.example.amai.core.chat.entity;

import com.example.amai.core.admin_user.entity.Account;
import com.example.amai.core.chat.entity.listener.ChatListener;
import lombok.*;

import javax.persistence.*;

@Entity
@EntityListeners(ChatListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    /**
     * ID dịch vụ
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Người chat
     */
    @ManyToOne
    @JoinColumn(name = "created_by_user_name")
    private Account createdBy;

    /**
     * Thời gian chat
     */
    private String createAt;

    /**
     * Người rep mạc định là tiệm trà sữa amai
     */
    private String updatedBy;

    /**
     * Thời gian rep
     */
    private String updateAt;

    /**
     * Cờ xóa
     */
    private Boolean isDeleted;

    /**
     * nội dung tin nhắn
     */
    private String msg;
    /**
     * nội dung tin nhắn chatbot rep
     */
    private String msgchatbot;
}
