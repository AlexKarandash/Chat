package client.impl;

import client.Client;
import common.listener.Listener;
import client.sender.MessageSender;
import common.enums.CommandMessage;
import common.json.bodymessage.BodyMessage;
import common.stream.StreamIO;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SimpleClientTest {

    StreamIO streamIO;
    MessageSender messageSender;
    Listener listener;

    @Before
    public void setUp() throws Exception {
        streamIO = mock(StreamIO.class);
        messageSender = mock(MessageSender.class);
        listener = mock(Listener.class);
    }

    @Test
    public void testStart() throws Exception {
        when(streamIO.read()).thenReturn(CommandMessage.EXIT.getTextCommand());
        when(messageSender.sendMessage(any(BodyMessage.class))).thenReturn(true);
        Client client = new SimpleClient(60001, messageSender, streamIO, listener);

        client.start();

        verify(listener).setDaemon(true);
        verify(listener).start();
        verify(messageSender).sendMessage(any(BodyMessage.class));
    }

    @Test
    public void testLogin() throws Exception {
        Client client = new SimpleClient(60001, messageSender, streamIO, listener);

        client.login("Alex");

        verify(messageSender).sendMessage(any(BodyMessage.class));
    }

    @Test
    public void testLogoutAndStop() throws Exception {
        when(messageSender.sendMessage(any(BodyMessage.class))).thenReturn(true);
        Client client = new SimpleClient(60001, messageSender, streamIO, listener);

        client.logoutAndStop();

        verify(messageSender).sendMessage(any(BodyMessage.class));
        verify(listener).interrupt();
    }

    @Test
    public void testSendMessage() throws Exception {
        when(messageSender.sendMessage(any(BodyMessage.class))).thenReturn(true);
        Client client = new SimpleClient(60001, messageSender, streamIO, listener);

        client.sendMessage("test");

        verify(messageSender).sendMessage(any(BodyMessage.class));
    }
}
