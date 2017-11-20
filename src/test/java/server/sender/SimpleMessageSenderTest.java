package server.sender;

import common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;
import server.client.ChatClient;
import server.client.ChatClients;
import server.client.ConcurrentHashMapChatClients;
import server.handler.MessageQueue;
import server.lastmessages.CircularFifoBufferLastMessages;
import server.lastmessages.LastMessages;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SimpleMessageSenderTest {

    @Test
    //отправка сообщения всем пользователям
    //сообщение должно добавиться в список последних сообщений
    //а так же messageQueue.add должен вызваться один раз, так как самому Alex1 отправлять сообщение не надо
    public void testSendMessageToClients() throws Exception {
        LastMessages lastMessages = new CircularFifoBufferLastMessages(2);
        ChatClients chatClients = new ConcurrentHashMapChatClients();
        chatClients.addUser(new ChatClient("Alex1","",0));
        chatClients.addUser(new ChatClient("Alex2","",0));
        MessageQueue<BodyMessage> messageQueue = mock(MessageQueue.class);

        MessageSender messageSender = new SimpleMessageSender(lastMessages, messageQueue);
        messageSender.sendMessageToClients("Alex1","Hello", chatClients);

        Assert.assertEquals(lastMessages.getLastMessages().size(), 1);
        verify(messageQueue).add(any(BodyMessage.class));
    }

    @Test
    //отправка сообщения самому пользователю
    //messageQueue.add должен вызваться один раз
    public void testSendMessage() throws Exception {
        LastMessages lastMessages = mock(LastMessages.class);
        MessageQueue<BodyMessage> messageQueue = mock(MessageQueue.class);

        MessageSender messageSender = new SimpleMessageSender(lastMessages, messageQueue);
        messageSender.sendMessage(new ChatClient("Alex1", "", 0), "Hello");

        verify(messageQueue).add(any(BodyMessage.class));
    }

    @Test
    //отправка списка последних сообщений самому пользователю
    //messageQueue.add должен вызваться один раз
    public void testSendLastMessages() throws Exception {
        LastMessages lastMessages = new CircularFifoBufferLastMessages(2);
        lastMessages.add("1");
        MessageQueue<BodyMessage> messageQueue = mock(MessageQueue.class);

        MessageSender messageSender = new SimpleMessageSender(lastMessages, messageQueue);
        messageSender.sendLastMessages(new ChatClient("Alex1", "", 0));

        verify(messageQueue).add(any(BodyMessage.class));
    }
}
