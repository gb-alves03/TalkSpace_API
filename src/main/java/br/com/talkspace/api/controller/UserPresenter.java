package br.com.talkspace.api.controller;

import br.com.talkspace.api.database.model.User;
import br.com.talkspace.api.dto.user.UserDtoResponse;
import org.springframework.stereotype.Component;

@Component
public class UserPresenter {

    public UserDtoResponse presentUser(User user) {
        String userName = user.getFirstName() + " " + user.getLastName();

        return new UserDtoResponse(user.getId(), userName, user.getEmail(), user.getAvatar().toString());
    }
}
