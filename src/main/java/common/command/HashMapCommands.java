package common.command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//класс для работы со списком команд
public class HashMapCommands implements Commands {
    Map<String, Command> commands = new HashMap<String, Command>();

    //добавить команду
    public void add(Command command) {
        commands.put(command.getName(), command);
    }

    //удалить команду по имени
    public void remove(String name) {
        commands.remove(name);
    }

    //кол-во команд
    public int size() {
        return commands.size();
    }

    //получить список юзеров
    public Collection<Command> getCommands() {
        return commands.values();
    }

    //проверить содержится ли команда в списке по имени
    public boolean contains(String name) {
        return commands.containsKey(name);
    }

    //получить команду по значению
    public Command getByName(String name) {
        return commands.get(name);
    }

}
