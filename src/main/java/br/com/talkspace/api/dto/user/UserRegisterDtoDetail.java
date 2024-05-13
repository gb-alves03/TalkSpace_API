package br.com.talkspace.api.dto.user;

import br.com.talkspace.api.database.model.User;

public record UserRegisterDtoDetail(
        String name,
        String email
) {
    public UserRegisterDtoDetail(User user) {
        this(user.getName(), user.getEmail());
    }
}
