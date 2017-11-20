package server.sender;

import common.enums.CommandMessage;
import common.json.MessageFactory;
import common.json.bodymessage.BodyMessage;
import server.client.ChatClient;
import server.client.ChatClients;

import java.io.IOException;

import server.handler.MessageQueue;
import server.lastmessages.LastMessages;

//класс для отправки сообщений
public class SimpleMessageSender implements MessageSender {

    private LastMessages lastMessages;
    private MessageQueue<BodyMessage> messageQueue;

    public SimpleMessageSender(LastMessages lastMessages, MessageQueue<BodyMessage> messageQueue) {
        this.lastMessages = lastMessages;
        this.messageQueue = messageQueue;
    }

    //Пользователь отправляет сообщение
    public void sendMessageToClients(String nickName, String message, ChatClients chatClients) throws IOException {
        String messageWrap = messageWrapper(nickName, message);
        addLastMessage(messageWrap);
        for (ChatClient chatClient : chatClients.getUsers()) {
            if (!chatClient.getNickName().equals(nickName)) {
                sendMessage(chatClient, messageWrap);
            }
        }
    }

    //отправить сообщение пользователю
    public void sendMessage(ChatClient chatClient, String message) throws IOException {
        BodyMessage bodyMessage = MessageFactory.createBodyMessage(CommandMessage.MESSAGE.getTextCommand(),
                chatClient.getNickName(), message, chatClient.getIp(), chatClient.getPort());
        addMessageQueue(bodyMessage);
    }

    //выcлать пользователю последние сообщения
    public void sendLastMessages(ChatClient chatClient) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (String lastMessage : lastMessages.getLastMessages()) {
            stringBuilder.append(lastMessage).append(" \n");
        }
        if (stringBuilder.length() != 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            sendMessage(chatClient, stringBuilder.toString());
        }
    }

    //добавить сообщение в список последних
    private void addLastMessage(String message) {
        lastMessages.add(message);
    }

    //добавить ответ в очередь
    private void addMessageQueue(BodyMessage bodyMessage) {
        messageQueue.add(bodyMessage);
    }

    //обернуть сообщение
    private String messageWrapper(String nickName, String message) {
        return nickName + ": " + message;
    }

}
