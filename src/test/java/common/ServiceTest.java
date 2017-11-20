package common;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

//возвращаемый экземпляр не должен быть null
public class ServiceTest {

    Service service;

    @Before
    public void setUp() throws Exception {
        service = Service.getInstance();
    }

    @Test
    public void testGetService() throws Exception {
        Assert.assertNotNull(service);
    }

    @Test
    public void testGetHelloMessageForAll() throws Exception {
        Assert.assertNotNull(service.getHelloMessageForAll());
        Assert.assertTrue(service.getHelloMessageForAll().length()>0);
    }

    @Test
    public void testGetGoodbayMessageForAll() throws Exception {
        Assert.assertNotNull(service.getGoodbayMessageForAll());
        Assert.assertTrue(service.getGoodbayMessageForAll().length()>0);
    }

    @Test
    public void testGetLineSeparator() throws Exception {
        Assert.assertNotNull(service.getLineSeparator());
        Assert.assertTrue(service.getLineSeparator().length()>0);
    }

    @Test
    public void testGetMaxCountConnections() throws Exception {
        Assert.assertNotNull(service.getMaxCountConnections());
    }

    @Test
    public void testGetLastMessagesLength() throws Exception {
        Assert.assertNotNull(service.getLastMessagesLength());
    }

    @Test
    public void testGetMaxThreadsResponse() throws Exception {
        Assert.assertNotNull(service.getMaxThreadsResponse());
    }

    @Test
    public void testGetMaxThreadsRequest() throws Exception {
        Assert.assertNotNull(service.getMaxThreadsRequest());
    }

    @Test
    public void testGetMinPort() throws Exception {
        Assert.assertNotNull(service.getMinPort());
    }

    @Test
    public void testGetMaxPort() throws Exception {
        Assert.assertNotNull(service.getMaxPort());
    }

    @Test
    public void testGetEncoding() throws Exception {
        Assert.assertNotNull(service.getEncoding());
        Assert.assertTrue(service.getEncoding().length()>0);
    }
}
