package br.com.talkspace.api.database.model;

import br.com.talkspace.api.enums.MessageType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column
    private String room;

    @Column
    private String sender;

    @Column
    private String targetUsername;

    @Column
    private String message;

    @Column
    private LocalDateTime createdAt;
}
