package br.com.talkspace.api.database.repository;

import br.com.talkspace.api.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    UserDetails findByLogin(String login);
    Optional<User> findByEmail(String email);
}
