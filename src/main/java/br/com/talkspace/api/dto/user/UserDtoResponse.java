package br.com.talkspace.api.dto.user;

import br.com.talkspace.api.database.model.User;

import java.util.UUID;

public record UserDtoResponse(
        UUID id,
        String name,
        String email,
        String password,
        String avatar) {

    public UserDtoResponse(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getAvatar());
    }
}
