package br.com.talkspace.api.socket;

import br.com.talkspace.api.database.model.Message;
import br.com.talkspace.api.database.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SendMessageUseCase {

    @Autowired
    private final MessageRepository messageRepository;

    public SendMessageUseCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public SendMessageResult execute(SendMessageInput input) {
        try {
            if (input.getContent() == null || input.getContent().isEmpty()) {
                return SendMessageResult.failure("Content cannot be empty");
            }

            if (input.getOwner() == null || input.getOwner().isEmpty()) {
                return SendMessageResult.failure("Owner can't be empty");
            }

            Message message = new Message();

            messageRepository.save(message);

            return SendMessageResult.success("Message was sent with success: " + input.getContent());
        } catch (Exception e) {
            return SendMessageResult.failure("Failed to sent message: " + e.getMessage());
        }
    }

}
