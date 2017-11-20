package server.sender;

import server.client.ChatClient;
import server.client.ChatClients;

import java.io.IOException;

//интерфейс для работы с сообщениями
public interface MessageSender {
    public void sendMessageToClients(String nickName, String message, ChatClients chatClients) throws IOException;

    public void sendMessage(ChatClient chatClient, String message) throws IOException;

    public void sendLastMessages(ChatClient chatClient) throws IOException;
}
