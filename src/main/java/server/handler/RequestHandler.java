package server.handler;

import common.command.Command;
import common.command.Commands;
import common.enums.Status;
import common.json.bodymessage.BodyMessageJsonSerializer;
import common.json.jsonSerializer;
import common.transport.TransportConnection;
import common.json.bodymessage.BodyMessage;

import java.io.IOException;

//класс обработки запроса на основе соединения
public class RequestHandler implements MessageHandler<TransportConnection> {

    private Commands systemCommands;
    private jsonSerializer<BodyMessage> jsonSerializer;

    public RequestHandler(Commands systemCommands) {
        this.systemCommands = systemCommands;
        jsonSerializer = new BodyMessageJsonSerializer();
    }

    //распарсить сообщение
    public void handle(TransportConnection transportConnection) {
        Status status = Status.ERROR;
        try {
            //получаем сообщение из соединения
            String message = transportConnection.receive();
            BodyMessage bodyMessage = jsonSerializer.deserialize(message);
            String ip = transportConnection.getIp();
            bodyMessage.setIp(ip);

            if (systemCommands.contains(bodyMessage.getCommand())) {// проверка системных команд
                Command systemCommand = systemCommands.getByName(bodyMessage.getCommand());
                status = systemCommand.execute(bodyMessage);
            }

        } catch (IOException e) {
            status = Status.ERROR;
            System.err.println("Error processing request");
        } finally {
            try {
                transportConnection.send(status.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
