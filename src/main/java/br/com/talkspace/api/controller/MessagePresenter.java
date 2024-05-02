package br.com.talkspace.api.controller;

import br.com.talkspace.api.database.model.Message;
import br.com.talkspace.api.dto.message.MessageDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class MessagePresenter {

    public MessageDtoResponse presentMessage(Message message) {

        return new MessageDtoResponse(message.getId(), message.getMessage(), message.getCreatedAt(), message.getSender(), message.getTargetUsername().toString());
    }
}
