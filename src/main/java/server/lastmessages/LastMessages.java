package server.lastmessages;

import java.util.Collection;

//интерфейс для работы со списком сообщений
public interface LastMessages {
    public void clear();

    public void add(String message);

    public Collection<String> getLastMessages();
}
