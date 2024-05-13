package br.com.talkspace.api.service;

import br.com.talkspace.api.database.model.Message;
import br.com.talkspace.api.database.model.User;
import br.com.talkspace.api.database.repository.UserRepository;
import br.com.talkspace.api.socket.OnlineUser;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Getter
public class SocketService {

    private List<OnlineUser> connectedUsers = new ArrayList<>();
    private final UserRepository repository;

    @Autowired
    public SocketService(UserRepository repository) {
        this.repository = repository;
    }

    public OnlineUser addUser(String userId, String socketId) {
        Optional<User> userOptional = repository.findById(UUID.fromString(userId));

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            OnlineUser onlineUser = new OnlineUser(
                    user.getId().toString(),
                    socketId,
                    user.getName(),
                    user.getEmail(),
                    user.getAvatar() != null ? user.getAvatar() : ""
            );
            connectedUsers.add(onlineUser);
            return onlineUser;
        }
        return null;
    }

    public void removeUser(String userId) {
        connectedUsers.removeIf(user -> user.getId().equals(userId));
    }

}
