package br.com.talkspace.api.controller;

import br.com.talkspace.api.database.model.User;
import br.com.talkspace.api.dto.user.UserRegisterDtoDetail;
import br.com.talkspace.api.dto.user.UserRegisterDtoRequest;
import br.com.talkspace.api.database.repository.UserRepository;
import br.com.talkspace.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository repository;

    private final UserService service;

    @PostMapping
    @Transactional
    public ResponseEntity signUp(@RequestBody @Valid UserRegisterDtoRequest userData) {
        var user = new User(userData);

        try {
            service.registerUser(user);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
