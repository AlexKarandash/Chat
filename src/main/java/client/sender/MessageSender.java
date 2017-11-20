package client.sender;

import common.json.bodymessage.BodyMessage;

import java.io.IOException;

//интерфейс отправки сообщений клиентом
public interface MessageSender {
    public boolean sendMessage(BodyMessage bodyMessage) throws IOException;
}
