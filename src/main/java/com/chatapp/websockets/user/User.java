package com.chatapp.websockets.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class User {
    private String nickName;
    private String fullName;
    private Status status;

}
