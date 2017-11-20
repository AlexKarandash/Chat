package server.handler;

import common.Service;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import server.client.ChatClients;
import common.json.bodymessage.BodyMessage;

import java.io.IOException;

//класс обработки ответа на основе сокетов
public class ResponseHandler implements MessageHandler<BodyMessage> {
    private ChatClients chatClients;
    private TransportFactory transportFactory;

    public ResponseHandler(ChatClients chatClients, TransportFactory transportFactory) {
        this.chatClients = chatClients;
        this.transportFactory = transportFactory;
    }

    //распарсить сообщение
    public void handle(BodyMessage bodyMessage) {
        try {
            TransportConnection transportConnection = transportFactory.createConnection(bodyMessage.getIp(),
                    bodyMessage.getPort(), Service.getInstance().getEncoding());
            transportConnection.send(bodyMessage.getText());
        } catch (IOException e) {
            System.err.println("Break the connection with the client");
            chatClients.removeUser(bodyMessage.getNickName());
        }
    }
}
