package server.impl;

import common.listener.SimpleBaseListener;
import common.listener.Listener;
import common.stream.StreamIO;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import server.handler.MessageQueue;

import java.io.IOException;

//класс слушатель входящих сообщений
public class SimpleServerListener extends SimpleBaseListener implements Listener {

    MessageQueue<TransportConnection> requestQueue;

    public SimpleServerListener(int portIn, StreamIO streamIO, TransportFactory transportFactory, MessageQueue<TransportConnection> requestQueue) {
        super(portIn, streamIO, transportFactory);
        this.requestQueue = requestQueue;
    }

    @Override
    protected void worker() throws IOException {
        requestQueue.add(transportListener.accept());
    }
}
