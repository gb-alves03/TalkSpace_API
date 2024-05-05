package br.com.talkspace.api.dto.user;

import br.com.talkspace.api.database.model.User;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.UUID;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.context.annotation.Bean;

public record UserRegisterDtoRequest(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        @Email
        @Column(unique = true)
        String email,
        @NotBlank
        @Valid
        @Pattern(regexp = "^[a-zA-Z0-9]{8,12}$", message = "Password must be of 8 to 12 lenght with no special characters!")
        String password,
        String avatar
) {
}
