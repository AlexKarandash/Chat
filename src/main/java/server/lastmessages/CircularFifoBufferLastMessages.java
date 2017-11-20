package server.lastmessages;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.CircularFifoBuffer;

import java.util.Collection;

//реализация работы со списком последних сообщений через CircularFifoBuffer
public class CircularFifoBufferLastMessages implements LastMessages {

    private Buffer lastMessages;

    public CircularFifoBufferLastMessages(int lastMessagesLength) {
        lastMessages = BufferUtils.synchronizedBuffer(new CircularFifoBuffer(lastMessagesLength));
    }

    @Override
    public void clear() {
        lastMessages.clear();
    }

    @Override
    public void add(String message) {
        lastMessages.add(message);
    }

    @Override
    public Collection<String> getLastMessages() {
        return lastMessages;
    }
}
