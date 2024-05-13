package br.com.talkspace.api.socket;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SendMessageResult {
    private boolean success;
    private String message;

    public static SendMessageResult success(String message) {
        return new SendMessageResult(true, message);
    }

    public static SendMessageResult failure(String message) {
        return new SendMessageResult(false, message);
    }
}
