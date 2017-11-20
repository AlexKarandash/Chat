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

public class TcpSocketTransportConnectionTest {

    Thread thread;
    TransportConnection transportConnection;
    TransportListener transportListener;

    //запустить клиента в потоке
    private Thread getThread(){
        return new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TransportFactory transportFactory = new TcpSocketTransportFactory();
                    transportListener = transportFactory.createListener(60005, 1000,
                            Service.getInstance().getEncoding());
                    TransportConnection transportConnection = transportListener.accept();
                    transportConnection.send("test");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Before
    public void setUp() throws Exception {
        thread = getThread();
        thread.start();
        transportConnection = new TcpSocketTransportConnection("127.0.0.1",
                60005, Service.getInstance().getEncoding());
    }

    @After
    public void tearDown() throws Exception {
        if (transportConnection != null) transportConnection.close();
        if (transportListener != null) transportListener.close();
        if (thread != null) thread.interrupt();
    }

    @Test
    public void testGetIp() throws Exception {
        String ip = transportConnection.getIp();

        Assert.assertEquals(ip, "127.0.0.1");
    }

    @Test
    public void testReceive() throws Exception {
        String text = transportConnection.receive();

        Assert.assertEquals(text, "test");
    }

    @Test
    public void testSend() throws Exception {
        transportConnection.send("test");
    }

    @Test
    public void testClose() throws Exception {
        transportConnection.close();
    }
}
