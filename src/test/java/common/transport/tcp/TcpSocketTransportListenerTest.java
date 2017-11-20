package common.transport.tcp;

import common.Service;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import common.transport.TransportListener;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.SocketException;

public class TcpSocketTransportListenerTest {

    Thread thread;
    TransportConnection transportConnection;
    TransportListener transportListener;

    //запустить клиента в потоке
    private Thread getThread(){
       return new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               try {
                   TransportFactory transportFactory = new TcpSocketTransportFactory();
                   transportFactory.createConnection("127.0.0.1",60000, Service.getInstance().getEncoding());
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });
    }

    @Before
    public void setUp() throws Exception {
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        transportListener = transportFactory.createListener(60000, 1000,
                Service.getInstance().getEncoding());
    }

    @After
    public void tearDown() throws Exception {
        if (thread != null) thread.interrupt();
        if (transportConnection != null) transportConnection.close();
        if (transportListener != null) transportListener.close();
    }

    @Test
    //запускаем слушателя и клиент для подключения
    //accept слушателя должен получить не пустой объект и тип TcpSocketTransportConnection
    public void testAccept() throws Exception {
        thread = getThread();
        thread.start();

        transportConnection = transportListener.accept();

        Assert.assertNotNull(transportConnection);
        Assert.assertTrue(transportConnection instanceof TcpSocketTransportConnection);
    }

    @Test(expected = SocketException.class)
    //после закрытия сокета, попытка получить подключение должна выдать ошибку SocketException
    public void testClose() throws Exception {
        transportListener.close();
        transportListener.accept();
    }
}
