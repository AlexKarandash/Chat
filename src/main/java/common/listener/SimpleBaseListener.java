package common.listener;

import common.Service;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.TransportListener;

import java.io.IOException;

//класс слушатель входящих сообщений
public abstract class SimpleBaseListener extends Thread implements Listener {

    //переменные соединения
    protected TransportListener transportListener;
    protected StreamIO streamIO;

    //инициализация слушателя
    public SimpleBaseListener(int portIn, StreamIO streamIO, TransportFactory transportFactory) {
        try {
            this.streamIO = streamIO;
            transportListener = transportFactory.createListener(portIn,
                    Service.getInstance().getMaxCountConnections(),
                    Service.getInstance().getEncoding());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //ждем сообщения и выводим
    public void run() {
        try {
            while (!isInterrupted()) {
                worker();
            }
            transportListener.close();
        } catch (IOException e) {
            System.err.println("Ошибка при получении сообщения");
            e.printStackTrace();
        }
    }

    protected abstract void worker() throws IOException;
}
