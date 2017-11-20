package common.command;

import server.client.ChatClients;
import server.sender.MessageSender;

//фабрика команд
public class CommandFactory {

    public static Command getCountUserCommand(MessageSender messageSender, ChatClients chatClients){
        return new CountUserCommand(messageSender, chatClients);
    }

    public static Command getExitCommand(MessageSender messageSender, ChatClients chatClients) {
        return new ExitCommand(messageSender, chatClients);
    }

    public static Command getHelpCommand(Commands commands, MessageSender messageSender, ChatClients chatClients) {
        return new HelpCommand(commands, messageSender, chatClients);
    }

    public static Command getLoginCommand(MessageSender messageSender, ChatClients chatClients) {
        return new LoginCommand(messageSender, chatClients);
    }

    public static Command getMessageCommand(Commands commands, MessageSender messageSender, ChatClients chatClients) {
        return new MessageCommand(commands, messageSender, chatClients);
    }

    public static Commands getHashMapCommands() {
        return new HashMapCommands();
    }
}
