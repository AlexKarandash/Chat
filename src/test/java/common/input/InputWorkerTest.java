package common.input;

import common.stream.ConsoleStreamIO;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class InputWorkerTest {

    TransportFactory transportFactory;

    private StreamIO getStream(String Value){
        ByteArrayInputStream in = new ByteArrayInputStream(Value.getBytes());
        System.setIn(in);
        return new ConsoleStreamIO(new Scanner((System.in)), System.out);
    }

    @Before
    public void setUp() throws Exception {
        transportFactory = new TcpSocketTransportFactory();
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(System.in);
    }

    @Test
    //введенное с консоли 60001 должно равняться результату метода 60001
    public void testGetPortServer_Input60001EqualOutput60001_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("60001");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        int port = inputWorker.getPortServer();

        Assert.assertEquals(port, 60001);
    }

    @Test
    //введенное с консоли 127.0.0.1 должно равняться результату метода 127.0.0.1
    public void testGetIpServer_Input127001EqualOutput127001_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("127.0.0.1");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        String ip = inputWorker.getIpServer();

        Assert.assertEquals(ip, "127.0.0.1");
    }

    @Test
    //введенное с консоли 60001 должно равняться результату метода 60001
    public void testGetPortToServer_Input60001EqualOutput60001_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("60001");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        int port = inputWorker.getPortToServer();

        Assert.assertEquals(port, 60001);
    }

    @Test
    //введенное с консоли 60001 должно равняться результату метода 60001
    public void testGetPortClient_Input60001EqualOutput60001_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("60001");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        int port = inputWorker.getPortClient();

        Assert.assertEquals(port, 60001);
    }

    @Test
    //введенное с консоли Alex должно равняться результату метода Alex
    public void testGetNickName_InputAlexEqualOutputAlex_TrueReturned() throws Exception {
        StreamIO streamIO = getStream("Alex");
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);

        String nickName = inputWorker.getNickName("");

        Assert.assertEquals(nickName, "Alex");
    }
}
