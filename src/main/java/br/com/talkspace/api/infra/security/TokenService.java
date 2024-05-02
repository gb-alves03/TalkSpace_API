package br.com.talkspace.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import br.com.talkspace.api.database.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret")
    private String secret;

    public String generateToken(User user) {
        /*chamar o banco e verificar se e-mail existe,
        senão existir throw new exception

        criptografar a senha e verificar se bate com a que está armazenada na
        variável desse usuário

        caso seja diferente lançar exceção
         */
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
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
