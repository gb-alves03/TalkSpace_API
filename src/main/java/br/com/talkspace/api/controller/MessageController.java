package br.com.talkspace.api.controller;

import br.com.talkspace.api.database.model.Message;
import br.com.talkspace.api.dto.message.MessageDto;
import br.com.talkspace.api.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping
    @Transactional
    public ResponseEntity sendMessage(@RequestBody @Valid MessageDto messageData) {
        var message = new Message(messageData);

        try {
            service.saveMessage(message);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
