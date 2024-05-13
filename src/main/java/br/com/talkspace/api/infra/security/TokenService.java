package br.com.talkspace.api.infra.security;

import br.com.talkspace.api.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import br.com.talkspace.api.database.model.User;

import java.time.Duration;
import java.time.Instant;


@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    @Lazy
    @Autowired
    private UserService userService;


    public String generateToken(User user) {

        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("TalkSpace API")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Generation JWT Token error", exception.getCause());
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("TalkSpace API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid or expired Token JWT!");
        }

    }

    private Instant expirationDate() {
        Instant expiration = Instant.now().plus(Duration.ofHours(2));
        return expiration;
    }
}

