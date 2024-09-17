package com.chatapp.websockets.chat;

import com.chatapp.websockets.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageRepository repository;
    private final ChatRoomService chatRoomService;

    public ChatMessage save(ChatMessage chatMessage) {
        var chatId = chatRoomService.getChatRoomId(
                chatMessage.getSenderId(),
                chatMessage.getReceiverId(),
                true
        ).orElseThrow(); //Create dedicated exception

        chatMessage.setChatId(chatId);
        repository.save(chatMessage);

        return chatMessage;
    }

    public List<ChatMessage> findChatMessages(String senderId, String receiverId) {
        var chatRoomId = chatRoomService.getChatRoomId(senderId, receiverId, false);
        return chatRoomId.map(repository::findByChatId).orElse(new ArrayList<>());
    }
}
