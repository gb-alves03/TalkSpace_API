package br.com.talkspace.api.service;

import br.com.talkspace.api.database.model.User;
import br.com.talkspace.api.database.repository.UserRepository;
import br.com.talkspace.api.dto.user.UserRegisterDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void registerUser(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("This e-mail already exists!");
        }
        repository.save(user);
    }

}
