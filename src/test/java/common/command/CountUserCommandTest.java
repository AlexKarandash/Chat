package common.command;

import common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;
import server.client.ChatClient;
import server.client.ChatClients;
import server.sender.MessageSender;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CountUserCommandTest {

    @Test
    //В команде должен быть выполнен определенный набор методов объектов
    public void testExecute() throws Exception {
        MessageSender messageSender = mock(MessageSender.class);
        ChatClients chatClients = mock(ChatClients.class);
        BodyMessage bodyMessage = mock(BodyMessage.class);

        Command command = CommandFactory.getCountUserCommand(messageSender, chatClients);
        command.execute(bodyMessage);

        verify(bodyMessage).getNickName();
        verify(chatClients).getUser(anyString());
        verify(chatClients).getCountUser();
        verify(messageSender).sendMessage(any(ChatClient.class), anyString());
    }

    @Test
    //имя команды не должно быть пустым
    public void testGetName_NotEmpty() throws Exception {
        Command command = CommandFactory.getCountUserCommand(null, null);

        Assert.assertNotNull(command.getName());
        Assert.assertTrue(command.getName().length() > 0);
    }

    @Test
    //описание команды не должно быть пустым
    public void testGetDescription() throws Exception {
        Command command = CommandFactory.getCountUserCommand(null, null);

        Assert.assertNotNull(command.getDescription());
        Assert.assertTrue(command.getDescription().length() > 0);
    }
}
