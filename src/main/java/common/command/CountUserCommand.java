package common.command;

import common.enums.Status;
import server.client.ChatClient;
import server.client.ChatClients;
import common.json.bodymessage.BodyMessage;
import server.sender.MessageSender;

import java.io.IOException;

//Пользовательская команда COUNT_USER
public class CountUserCommand extends BaseCommand {

    public CountUserCommand(MessageSender messageSender, ChatClients chatClients) {
        super(messageSender, chatClients);
        name = "COUNT_USER";
        description = "Выводит количество пользователей в чате";
    }

    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {
        ChatClient chatClient = chatClients.getUser(bodyMessage.getNickName());
        messageSender.sendMessage(chatClient, String.valueOf(chatClients.getCountUser()));
        return Status.SUCCESS;
    }
}
