package com.chatapp.websockets.chat;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatNotification {
    private String chatId;
    private String senderId;
    private String receiverId;
    private String message;
}
