package server.handler;

import common.json.MessageFactory;
import common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LinkedListMessageQueueTest {

    @Test
    //после добавления в очередь сообщения должен вызваться один раз метод MessageHandler.handle
    public void testAdd() throws Exception {
        MessageHandler<BodyMessage> messageHandler = mock(MessageHandler.class);
        MessageQueue<BodyMessage>  messageQueue = new LinkedListMessageQueue<BodyMessage>(1,1,messageHandler);

        messageQueue.add(MessageFactory.createBodyMessage("","","","",0));
        Thread.sleep(1000);

        verify(messageHandler).handle(any(BodyMessage.class));
    }

    @Test
    //добавленное сообщение и вытянутое из очереди должны совпадать
    public void testGetNextObject() throws Exception {
        MessageHandler<BodyMessage> messageHandler = mock(MessageHandler.class);
        MessageQueue<BodyMessage>  messageQueue = new LinkedListMessageQueue<BodyMessage>(0,0,messageHandler);
        BodyMessage bodyMessage = MessageFactory.createBodyMessage("EXIT","Alex","Bay","127.0.0.1",0);
        messageQueue.add(bodyMessage);

        BodyMessage bodyMessageNew = messageQueue.getNextObject();

        Assert.assertEquals(bodyMessage, bodyMessageNew);
    }

    @Test
    //при инициализации пул потоков должен состоять из одного потока
    //после shutdown, размер пула должен быть равен нулю
    public void testShutdownAndGetCountThreads() throws Exception {
        MessageHandler<BodyMessage> messageHandler = mock(MessageHandler.class);
        MessageQueue<BodyMessage>  messageQueue = new LinkedListMessageQueue<BodyMessage>(1,1,messageHandler);
        int countBefore = messageQueue.getCountThreads();

        messageQueue.shutdown();
        int countAfter = messageQueue.getCountThreads();

        Assert.assertEquals(countBefore, 1);
        Assert.assertEquals(countAfter, 0);
    }
}
