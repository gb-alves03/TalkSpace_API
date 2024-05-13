package br.com.talkspace.api.controller;

import br.com.talkspace.api.database.model.User;
import br.com.talkspace.api.database.repository.UserRepository;
import br.com.talkspace.api.dto.user.UserAuthenticationData;
import br.com.talkspace.api.dto.user.UserRegisterDtoRequest;
import br.com.talkspace.api.infra.security.TokenJWTData;
import br.com.talkspace.api.infra.security.TokenService;
import br.com.talkspace.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    private final UserService service;

    @Autowired
    private UserRepository repository;

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity signUp(@RequestBody @Valid UserRegisterDtoRequest userData) {
        var user = new User(userData);

        try {
            if (this.repository.findByEmail(user.getEmail()) != null) {
                return ResponseEntity.badRequest().build();
            }
            String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());
            service.registerUser(user);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity sigIn(@RequestBody @Valid UserAuthenticationData data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.manager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenJWTData(token));
    }
}
