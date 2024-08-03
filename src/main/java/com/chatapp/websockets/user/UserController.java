package com.chatapp.websockets.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {
    public final UserService userService;

    @MessageMapping("/users.addUser")
    @SendTo("/users/topic")
    public User addUser(@Payload User user) {
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/users.disconnectUser")
    @SendTo("/users/topic")
    public User disconnectUser(@Payload User user) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers(){
        return ResponseEntity.ok(userService.findConnectedUsers());
    }
}
