package br.com.talkspace.api.socket;

import br.com.talkspace.api.service.SocketService;
import io.socket.client.Socket;
import io.socket.socketio.server.SocketIoServer;
import io.socket.socketio.server.SocketIoServerOptions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SocketGateway {

    public SocketIoServerOptions options = new SocketIoServerOptions();
    @Autowired
    private final SocketIoServer server;
    @Autowired
    private final SocketService socketService;
    @Autowired
    private final SendMessageUseCase sendMessageUseCase;

    public SocketGateway(SocketService socketService, SendMessageUseCase sendMessageUseCase, SocketIoServerOptions options, SocketIoServer server) {
        this.socketService = socketService;
        this.sendMessageUseCase = sendMessageUseCase;
        this.server = new SocketIoServer(options);
    }

    ser("connection", socket -> {
        if (userId != null) {
            this.socketService.addUser(userId, client.id());
             List<OnlineUser> onlineUsers = this.socketService.getConnectedUsers();
             client.("user:online", onlineUsers);
            System.out.println("Connect");
        }
    }

    public void handleDisconnect(Socket client, String userId) {
        if (userId != null) {
            this.socketService.removeUser(userId);
            List<OnlineUser> onlineUsers = this.socketService.getConnectedUsers();
            client.emit("user:online", onlineUsers);
            System.out.println("Disconnected");
        }
    }

    public void sendMessage(Socket client, String content, String owner) {
        SendMessageInput messageInput = new SendMessageInput(content, owner);
        this.sendMessageUseCase.execute(messageInput);
        client.emit("message:send", messageInput);
    }

    public void initializeListeners(Socket client, String userId) {
        client.on("connection", (Object... args) -> handleConnection(client, userId));
        client.on("disconnect", (Object... args) -> handleDisconnect(client, userId));
        client.on("message:send", (Object... args) -> {
            String content = (String) args[0];
            String owner = (String) args[1];
            sendMessage(client, content, owner);
        });
    }
}
