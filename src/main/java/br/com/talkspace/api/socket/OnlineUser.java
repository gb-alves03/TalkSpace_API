package br.com.talkspace.api.socket;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OnlineUser {
    private String id;
    private String socketId;
    private String name;
    private String email;
    private String avatarUrl;
}
