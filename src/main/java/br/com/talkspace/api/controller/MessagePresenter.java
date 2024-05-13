package br.com.talkspace.api.controller;

import br.com.talkspace.api.database.model.Message;
import br.com.talkspace.api.dto.message.MessageDto;
import org.springframework.stereotype.Component;

@Component
public class MessagePresenter {

    public MessageDto presentMessage(Message message) {

        return new MessageDto(message.getId(), message.getOwner(), message.getMessage(), message.getCreatedAt());
    }
}
