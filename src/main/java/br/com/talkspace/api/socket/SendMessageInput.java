package br.com.talkspace.api.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SendMessageInput {
    private String content;
    private String owner;
}
