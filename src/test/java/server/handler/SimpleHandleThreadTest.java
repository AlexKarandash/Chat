package server.handler;

import common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SimpleHandleThreadTest {

    @Test
    //Флаг потока isAlive до остановки должен быть True после False
    public void testKillThread() throws Exception {
        MessageQueue<BodyMessage> messageQueue = mock(MessageQueue.class);
        MessageHandler<BodyMessage> messageHandler = mock(MessageHandler.class);
        when(messageQueue.getNextObject()).thenReturn(new BodyMessage());

        SimpleHandleThread handleThread = new SimpleHandleThread<BodyMessage>(messageQueue, messageHandler);
        handleThread.start();
        Boolean stateBefore = handleThread.isAlive();
        handleThread.killThread();
        Thread.sleep(1000);
        Boolean stateAfter = handleThread.isAlive();

        Assert.assertTrue(stateBefore);
        Assert.assertFalse(stateAfter);
    }

    @Test
    //после старта поток должен вытащить сообщение из очереди и обработать
    public void testRun() throws Exception {
        MessageQueue<BodyMessage> messageQueue = mock(MessageQueue.class);
        MessageHandler<BodyMessage> messageHandler = mock(MessageHandler.class);
        when(messageQueue.getNextObject()).thenReturn(new BodyMessage());

        HandleThread handleThread = new SimpleHandleThread<BodyMessage>(messageQueue, messageHandler);
        handleThread.start();
        Thread.sleep(1000);

        verify(messageQueue, atLeast(1)).getNextObject();
        verify(messageHandler, atLeast(1)).handle(any(BodyMessage.class));
    }
}
