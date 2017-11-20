package server.client;

import java.util.Collection;

//интерфейс для работы с пользователями чата
public interface ChatClients {

    //очистить список
    public void clear();

    //удалить юзера
    public void removeUser(String nickName);

    //добавить юзера
    public void addUser(ChatClient chatClient);

    //проверить существует ли юзер
    public boolean containsUser(String nickName);

    //получить юзера по нейму
    public ChatClient getUser(String nickName);

    //получить количество юзеров
    public int getCountUser();

    //получить список юзеров
    public Collection<ChatClient> getUsers();
}
