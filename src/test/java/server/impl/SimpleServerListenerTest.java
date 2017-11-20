package server.impl;

import common.listener.Listener;
import common.Service;
import common.stream.StreamIO;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import common.transport.TransportListener;
import org.junit.Test;
import server.handler.MessageQueue;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;


public class SimpleServerListenerTest {
    @Test
    public void testRun() throws Exception {
        MessageQueue messageQueue = mock(MessageQueue.class);
        StreamIO streamIO = mock(StreamIO.class);
        TransportFactory transportFactory = mock(TransportFactory.class);
        TransportListener transportListener = mock(TransportListener.class);
        when(transportFactory.createListener(60001, Service.getInstance().getMaxCountConnections(),
                Service.getInstance().getEncoding())).thenReturn(transportListener);
        TransportConnection transportConnection = mock(TransportConnection.class);
        when(transportListener.accept()).thenReturn(transportConnection);

        Listener listener = new SimpleServerListener(60001, streamIO, transportFactory, messageQueue);
        listener.setDaemon(true);
        listener.start();
        Thread.sleep(1000);
        listener.interrupt();

        verify(messageQueue, atLeast(1)).add(any(TransportConnection.class));
        verify(transportListener).close();
    }
}
