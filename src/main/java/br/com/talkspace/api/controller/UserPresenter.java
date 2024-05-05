package br.com.talkspace.api.controller;

import br.com.talkspace.api.database.model.User;
import br.com.talkspace.api.dto.user.UserDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class UserPresenter {

    public UserDtoResponse presentUser(User user) {

        return new UserDtoResponse(user.getId(), user.getName(), user.getEmail(), user.getAvatar().toString());
    }
}
