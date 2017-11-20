package common.transport;

import java.io.IOException;

//интерфейс соединения клиент-сервер
public interface TransportConnection {
    public String getIp() throws IOException;

    public String receive() throws IOException;

    public void send(String message) throws IOException;

    public void close() throws IOException;
}
