package common.command;

import server.client.ChatClients;
import server.sender.MessageSender;

//базовый класс абстрактных команд
public abstract class BaseCommand implements Command {
    protected String name;
    protected String description;
    protected MessageSender messageSender;
    protected ChatClients chatClients;

    public BaseCommand(MessageSender messageSender, ChatClients chatClients) {
        this.messageSender = messageSender;
        this.chatClients = chatClients;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

