package common.check;

import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;
import junit.framework.Assert;
import org.junit.Test;

//все результаты должны быть не пустые и соответствующего класса
public class CheckFactoryTest {
    @Test
    public void testGetBetweenNumberCheck() throws Exception {
        Check check = CheckFactory.getBetweenNumberCheck("10",0,20);

        Assert.assertNotNull(check);
        Assert.assertTrue(check instanceof BetweenNumberCheck);
    }

    @Test
    public void testGetIntegerCheck() throws Exception {
        Check check = CheckFactory.getIntegerCheck("10");

        Assert.assertNotNull(check);
        Assert.assertTrue(check instanceof IntegerCheck);
    }

    @Test
    public void testGetIpCheck() throws Exception {
        Check check = CheckFactory.getIpCheck("10");

        Assert.assertNotNull(check);
        Assert.assertTrue(check instanceof IpCheck);
    }

    @Test
    public void testPortIsNotUsedCheck() throws Exception {
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        Check check = CheckFactory.PortIsNotUsedCheck("10", transportFactory);

        Assert.assertNotNull(check);
        Assert.assertTrue(check instanceof PortIsNotUsedCheck);
    }

    @Test
    public void testGetPortCheck() throws Exception {
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        Check check = CheckFactory.getPortCheck("10", 0, 0, false, transportFactory);

        Assert.assertNotNull(check);
        Assert.assertTrue(check instanceof CheckList);
    }
}
