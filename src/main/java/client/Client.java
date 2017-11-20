package client;

import java.io.IOException;

//интерфейс клиента
public interface Client {
    public void start();

    public boolean login(String nickName) throws IOException;

    public void logoutAndStop() throws IOException;

    public void sendMessage(String message) throws IOException;

    public void startListener();
}
