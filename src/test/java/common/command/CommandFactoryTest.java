package common.command;

import junit.framework.Assert;
import org.junit.Test;

public class CommandFactoryTest {

    @Test
    public void testGetCountUserCommand() throws Exception {
        Command command = CommandFactory.getCountUserCommand(null, null);

        Assert.assertNotNull(command);
        Assert.assertTrue(command instanceof CountUserCommand);
    }

    @Test
    public void testGetExitCommand() throws Exception {
        Command command = CommandFactory.getExitCommand(null, null);

        Assert.assertNotNull(command);
        Assert.assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void testGetHelpCommand() throws Exception {
        Command command = CommandFactory.getHelpCommand(null, null, null);

        Assert.assertNotNull(command);
        Assert.assertTrue(command instanceof HelpCommand);
    }

    @Test
    public void testGetLoginCommand() throws Exception {
        Command command = CommandFactory.getLoginCommand(null, null);

        Assert.assertNotNull(command);
        Assert.assertTrue(command instanceof LoginCommand);
    }

    @Test
    public void testGetMessageCommand() throws Exception {
        Command command = CommandFactory.getMessageCommand(null, null, null);

        Assert.assertNotNull(command);
        Assert.assertTrue(command instanceof MessageCommand);
    }

    @Test
    public void testGetHashMapCommands() throws Exception {
        Commands commands = CommandFactory.getHashMapCommands();

        Assert.assertNotNull(commands);
        Assert.assertTrue(commands instanceof HashMapCommands);
    }
}
