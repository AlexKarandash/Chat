package client.impl;

import common.listener.SimpleBaseListener;
import common.listener.Listener;
import common.stream.StreamIO;
import common.transport.TransportConnection;
import common.transport.TransportFactory;

import java.io.IOException;

//класс слушатель входящих сообщений
public class SimpleClientListener extends SimpleBaseListener implements Listener {

    public SimpleClientListener(int portIn, StreamIO streamIO, TransportFactory transportFactory) {
        super(portIn, streamIO, transportFactory);
    }

    @Override
    protected void worker() throws IOException {
        TransportConnection transportConnection = transportListener.accept();
        String message = transportConnection.receive();
        streamIO.print(message);
        transportConnection.close();
    }

}
