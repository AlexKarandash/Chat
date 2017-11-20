package client.sender;

import common.Service;
import common.enums.Status;
import common.json.bodymessage.BodyMessageJsonSerializer;
import common.json.jsonSerializer;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import common.json.bodymessage.BodyMessage;

import java.io.IOException;

public class SimpleMessageSender implements MessageSender {

    private String ipServer;
    private int portServer;
    private TransportFactory transportFactory;
    private jsonSerializer<BodyMessage> jsonSerializer;

    public SimpleMessageSender(TransportFactory transportFactory, String ipServer, int portServer) {
        this.transportFactory = transportFactory;
        this.ipServer = ipServer;
        this.portServer = portServer;
        jsonSerializer = new BodyMessageJsonSerializer();
    }

    @Override
    //сформировать команду на сервер
    public boolean sendMessage(BodyMessage bodyMessage) throws IOException {
        String bodyMessageString = jsonSerializer.serialize(bodyMessage);
        TransportConnection transportConnection = transportFactory.createConnection(ipServer, portServer, Service.getInstance().getEncoding());
        transportConnection.send(bodyMessageString);
        Status status = Status.valueOf(transportConnection.receive());
        transportConnection.close();
        return status == Status.SUCCESS;
    }
}
