package br.com.talkspace.api.dto.message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDtoResponse(UUID id, String content, LocalDateTime createdAt, String sender, String targetUser) {
}
