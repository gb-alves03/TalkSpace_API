package br.com.talkspace.api.service;

import br.com.talkspace.api.database.model.User;
import br.com.talkspace.api.database.repository.UserRepository;
import br.com.talkspace.api.dto.user.UserRegisterDtoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository repository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void registerUser(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("This e-mail already exists!");
        }
        repository.save(user);
    }

    public boolean isValidUser(String email, String password) {
        try {
            User user = (User) repository.findByEmail(email);

            if (user == null) {
                return false;
            }

            boolean passwordFound = passwordEncoder.matches(password, user.getPassword());

            return passwordFound;
        } catch (Exception e)  {
            throw new RuntimeException();
        }
    }

}
