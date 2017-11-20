package common.command;

import common.Service;
import common.enums.CommandMessage;
import common.enums.Status;
import server.client.ChatClient;
import server.client.ChatClients;
import common.json.bodymessage.BodyMessage;
import server.sender.MessageSender;

import java.io.IOException;

//Системная команда авторизации LOGIN
public class LoginCommand extends BaseCommand {

    public LoginCommand(MessageSender messageSender, ChatClients chatClients) {
        super(messageSender, chatClients);
        name = CommandMessage.LOGIN.getTextCommand();
        description = "Попытка залогиниться";
    }

    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {
        String nickName = bodyMessage.getNickName();
        String ip = bodyMessage.getIp();
        int port = bodyMessage.getPort();

        Status status = chatClients.containsUser(nickName) ? Status.ERROR : Status.SUCCESS;
        if (status == Status.SUCCESS) { //проверяем используется ли уже это имя
            ChatClient chatClient = new ChatClient(nickName, ip, port);
            messageSender.sendLastMessages(chatClient);
            chatClients.addUser(chatClient);
            messageSender.sendMessageToClients(nickName, Service.getInstance().getHelloMessageForAll(), chatClients);
        }

        return status;
    }
}
