package br.com.talkspace.api.service;

import br.com.talkspace.api.database.model.Message;
import br.com.talkspace.api.database.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    List<Message> messages;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
        this.messages = new ArrayList<>();
    }

    public List<Message> findBySenderAndTarget(String sender, String target) {
        this.messages.stream().filter((message -> (message.getSender().toString().equals(sender) && message.getTarget().toString().equals(target)) ||
                (message.getSender().toString().equals(target) && message.getTarget().toString().equals(sender)))).toList(); {
                    return messageRepository.findBySenderAndTarget(sender, target);
        }
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }
}
