package server.client;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//реализация работы со списком пользователей чата через ConcurrentHashMap
public class ConcurrentHashMapChatClients implements ChatClients {
    private Map<String, ChatClient> chatClients = new ConcurrentHashMap<String, ChatClient>();

    //очистить список
    public void clear() {
        chatClients.clear();
    }

    //удалить юзера
    public void removeUser(String nickName) {
        chatClients.remove(nickName);
    }

    //добавить юзера
    public void addUser(ChatClient chatClient) {
        chatClients.put(chatClient.getNickName(), chatClient);
    }

    //проверить существует ли юзер
    public boolean containsUser(String nickName) {
        return chatClients.containsKey(nickName);
    }

    //получить юзера по нейму
    public ChatClient getUser(String nickName) {
        return chatClients.get(nickName);
    }

    //получить количество юзеров
    public int getCountUser() {
        return chatClients.size();
    }

    //получить список юзеров
    public Collection<ChatClient> getUsers() {
        return chatClients.values();
    }
}
