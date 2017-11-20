package common.command;

import java.util.Collection;

//интерфейс для работы со списком команд
public interface Commands {
    public void add(Command command);

    public void remove(String name);

    public int size();

    public Collection<Command> getCommands();

    public boolean contains(String name);

    public Command getByName(String name);
}
