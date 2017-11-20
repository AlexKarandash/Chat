package bot;

import client.Client;
import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BotTimerTaskTest {

    @Test
    public void testRun() throws Exception {
        BotTimerTask botTimerTask = new BotTimerTask();

        List<Client> clientList = new ArrayList<Client>();
        Client client = mock(Client.class);
        clientList.add(client);
        botTimerTask.setClients(clientList);
        botTimerTask.run();

        verify(client).sendMessage(anyString());
    }

    @Test
    //размер списка должен быть ноль
    public void testSetClients_SizeZeroReturned() throws Exception {
        BotTimerTask botTimerTask = new BotTimerTask();

        List<Client> clientList = new ArrayList<Client>();
        botTimerTask.setClients(clientList);

        Assert.assertEquals(botTimerTask.getClients().size(),0);
    }

    @Test
    //по умолчанию список пустой должен быть пустой
    public void testGetClients_NullReturned() throws Exception {
        BotTimerTask botTimerTask = new BotTimerTask();

        Assert.assertNull(botTimerTask.getClients());
    }
}
