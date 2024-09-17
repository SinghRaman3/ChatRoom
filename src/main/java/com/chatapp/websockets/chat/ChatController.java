package com.chatapp.websockets.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;
    private final HttpMessageConverters messageConverters;

    @MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessage chatMessage
    ){
        ChatMessage savedMsg = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiverId(),
                "/queue/messages",
                 ChatNotification.builder().
                         chatId(savedMsg.getChatId())
                         .senderId(savedMsg.getSenderId())
                         .receiverId(savedMsg.getReceiverId())
                         .message(savedMsg.getMessage())
                         .build()
        );
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<List<ChatMessage>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("receiverId") String receiverId
    ) {
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, receiverId));
    }
}
