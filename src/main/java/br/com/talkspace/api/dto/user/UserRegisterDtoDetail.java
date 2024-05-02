package br.com.talkspace.api.dto.user;

import br.com.talkspace.api.database.model.User;

public record UserRegisterDtoDetail(
        String firstName,
        String lastName,
        String email
) {
    public UserRegisterDtoDetail(User user) {
        this(user.getFirstName(), user.getLastName(), user.getEmail());
    }
}
