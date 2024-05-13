package br.com.talkspace.api.database.model;

import br.com.talkspace.api.dto.message.MessageDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "messages")
@Entity(name = "Message")
@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id = UUID.randomUUID();

    @Column
    private String owner;
    @Column
    private String message;
    @Column
    private LocalDateTime createdAt;

    public Message(MessageDto messageData) {
        this.owner = messageData.owner();
        this.message = messageData.message();
        this.createdAt = messageData.createdAt();
    }
}
