package server.handler;

import common.Service;
import common.json.MessageFactory;
import common.json.bodymessage.BodyMessage;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;
import org.junit.Test;
import server.client.ChatClients;
import server.client.ConcurrentHashMapChatClients;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResponseHandlerTest {

    @Test
    //должен создаться TransportConnection и вызваться метод send, все это по параметрам  BodyMessage
    public void testHandle() throws Exception {
        BodyMessage bodyMessage = MessageFactory.createBodyMessage("EXIT","Alex","Bay","127.0.0.1",0);
        TransportFactory transportFactory = mock(TransportFactory.class);
        TransportConnection transportConnection = mock(TransportConnection.class);
        when(transportFactory.createConnection(bodyMessage.getIp(),
                bodyMessage.getPort(), Service.getInstance().getEncoding())).thenReturn(transportConnection);

        ChatClients chatClients = new ConcurrentHashMapChatClients();
        MessageHandler<BodyMessage> messageHandler = new ResponseHandler(chatClients, transportFactory);
        messageHandler.handle(bodyMessage);

        verify(transportConnection).send(bodyMessage.getText());
    }

    @Test
    //При ошибке соединения с пользователем, пользователь из списка должен удалиться
    public void testHandle_Exception() throws Exception {
        BodyMessage bodyMessage = MessageFactory.createBodyMessage("EXIT","Alex","Bay","127.0.0.1",0);
        TransportFactory transportFactory = new TcpSocketTransportFactory();

        ChatClients chatClients = mock(ChatClients.class);
        MessageHandler<BodyMessage> messageHandler = new ResponseHandler(chatClients, transportFactory);
        messageHandler.handle(bodyMessage);

        verify(chatClients).removeUser(bodyMessage.getNickName());
    }

}
