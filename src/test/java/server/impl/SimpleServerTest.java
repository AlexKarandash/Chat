package server.impl;

import common.enums.CommandMessage;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;
import org.junit.Test;
import server.Server;

import static org.mockito.Mockito.*;

public class SimpleServerTest {

    @Test
    public void testStartAndStop() throws Exception {
        StreamIO streamIO = mock(StreamIO.class);
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        when(streamIO.read()).thenReturn(CommandMessage.EXIT.getTextCommand());

        Server server = new SimpleServer(60000, streamIO, transportFactory);
        server.start();
    }
}
