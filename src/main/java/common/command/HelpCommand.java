package common.command;

import common.enums.Status;
import server.client.ChatClient;
import server.client.ChatClients;
import common.json.bodymessage.BodyMessage;
import server.sender.MessageSender;

import java.io.IOException;

//Пользовательская команда HELP
public class HelpCommand extends BaseCommand {

    Commands commands;

    public HelpCommand(Commands commands, MessageSender messageSender, ChatClients chatClients) {
        super(messageSender, chatClients);
        name = "HELP";
        description = "Выводит все команды для работы с чатом";
        this.commands = commands;
    }

    @Override
    public Status execute(BodyMessage bodyMessage) throws IOException {
        ChatClient chatClient = chatClients.getUser(bodyMessage.getNickName());
        messageSender.sendMessage(chatClient, getHelpText());
        return Status.SUCCESS;
    }

    //получить текст командной подсказки
    private String getHelpText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Command command : commands.getCommands()) {
            stringBuilder.append(command.getName()).append(" - ").append(command.getDescription()).append("; \n");
        }
        if (stringBuilder.length() > 0)  stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

}
