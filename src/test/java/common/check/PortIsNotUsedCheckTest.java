package common.check;

import common.Service;
import common.transport.TransportFactory;
import common.transport.TransportListener;
import common.transport.tcp.TcpSocketTransportFactory;
import junit.framework.Assert;
import org.junit.Test;

public class PortIsNotUsedCheckTest {

    @Test
    //60001 должно пройти проверку
    public void testCheck_60001_TrueReturned() throws Exception {
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        Check check = CheckFactory.PortIsNotUsedCheck("60001", transportFactory);

        Boolean result = check.check();

        Assert.assertTrue(result);
    }

    @Test
    //60001 не должно пройти проверку
    public void testCheck_60001Used_FalseReturned() throws Exception {
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        TransportListener transportListener = transportFactory.createListener(60001, 1000, Service.getInstance().getEncoding());
        Check check = CheckFactory.PortIsNotUsedCheck("60001", transportFactory);

        Boolean result = check.check();

        Assert.assertFalse(result);
        transportListener.close();
    }

    @Test(expected = NumberFormatException.class)
    //abc не должно пройти проверку
    public void testCheck_abc_FalseReturned() throws Exception {
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        Check check = CheckFactory.PortIsNotUsedCheck("abc", transportFactory);

        Boolean result = check.check();

        Assert.assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    //null не должно пройти проверку
    public void testCheck_null_FalseReturned() throws Exception {
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        Check check = CheckFactory.PortIsNotUsedCheck(null, transportFactory);

        Boolean result = check.check();

        Assert.assertFalse(result);
    }
}
