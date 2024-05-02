package br.com.talkspace.api.dto.user;

import java.util.UUID;

public record UserDtoResponse(UUID id, String userName, String email, String avatar) {
}
