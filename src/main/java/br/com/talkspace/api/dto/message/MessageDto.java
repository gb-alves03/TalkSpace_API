package br.com.talkspace.api.dto.message;

import java.time.LocalDateTime;
import java.util.UUID;

public record MessageDto(UUID id, String sender, String target, String message, LocalDateTime createdAt) {
}
