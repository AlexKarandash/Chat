package common.command;

import common.Service;
import common.enums.CommandMessage;
import common.enums.Status;
import server.client.ChatClients;
import common.json.bodymessage.BodyMessage;
import server.sender.MessageSender;

import java.io.IOException;

//Пользовательская команда EXIT
public class ExitCommand extends BaseCommand {

    public ExitCommand(MessageSender messageSender, ChatClients chatClients) {
        super(messageSender, chatClients);
        name = CommandMessage.EXIT.getTextCommand();
        description = "Выход из чата";
    }

    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {
        String nickName = bodyMessage.getNickName();
        chatClients.removeUser(nickName);
        messageSender.sendMessageToClients(nickName, Service.getInstance().getGoodbayMessageForAll(), chatClients);
        return Status.SUCCESS;
    }
}
