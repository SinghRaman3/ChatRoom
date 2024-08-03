package com.chatapp.websockets.chatroom;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class ChatRoom {
    @Id
    public String id;
    public String chatId;
    public String senderId;
    public String receiverId;
}
