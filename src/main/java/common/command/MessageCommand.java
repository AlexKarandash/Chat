package common.command;

import common.enums.CommandMessage;
import common.enums.Status;
import server.client.ChatClients;
import common.json.bodymessage.BodyMessage;
import server.sender.MessageSender;

import java.io.IOException;

//Системная команда отправки сообщения MESSAGE
public class MessageCommand extends BaseCommand {

    Commands commands; //список пользовательских команд

    public MessageCommand(Commands commands, MessageSender messageSender, ChatClients chatClients) {
        super(messageSender, chatClients);
        name = CommandMessage.MESSAGE.getTextCommand();
        description = "Сообщение от пользователя";
        this.commands = commands;
    }

    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {
        Status status = Status.SUCCESS;
        String text = bodyMessage.getText();
        if (commands.contains(text)) { // проверка команд пользователя
            Command command = commands.getByName(text);
            command.execute(bodyMessage);
        } else {
            messageSender.sendMessageToClients(bodyMessage.getNickName(), text, chatClients);
        }

        return status;
    }
}
