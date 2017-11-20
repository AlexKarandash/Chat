package bot;

import common.Service;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class BotServiceTest {

    BotService botService;

    @Before
    public void setUp() throws Exception {
        botService = BotService.getInstance();
    }

    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(botService.getInstance());
    }

    @Test
    public void testGetIpServer() throws Exception {
        Assert.assertNotNull(botService.getIpServer());
        Assert.assertTrue(botService.getIpServer().length()>0);
    }

    @Test
    public void testGetPortServer() throws Exception {
        Assert.assertNotNull(botService.getPortServer());
    }

    @Test
    public void testGetCountUser() throws Exception {
        Assert.assertNotNull(botService.getCountUser());
    }

    @Test
    public void testGetStartPort() throws Exception {
        Assert.assertNotNull(botService.getStartPort());
    }

    @Test
    public void testGetIntervalConnect() throws Exception {
        Assert.assertNotNull(botService.getIntervalConnect());
    }

    @Test
    public void testGetTimerSendMessage() throws Exception {
        Assert.assertNotNull(botService.getTimerSendMessage());
    }
}
