package br.com.talkspace.api.dto.user;

public record UserAuthenticationData(
        String email,
        String password
) {
}
