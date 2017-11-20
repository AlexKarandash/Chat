package server.handler;

import common.command.Command;
import common.command.CommandFactory;
import common.command.Commands;
import common.enums.Status;
import common.json.bodymessage.BodyMessage;
import common.transport.TransportConnection;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RequestHandlerTest {

    @Test
    public void testHandle() throws Exception {
        TransportConnection transportConnection = mock(TransportConnection.class);
        Commands commands = mock(Commands.class);
        Command systemCommand = mock(Command.class);
        MessageHandler<TransportConnection> messageHandler = new RequestHandler(commands);

        when(transportConnection.receive()).thenReturn("{\"command\":\"MESSAGE\",\"nickName\":\"alex\",\"text\":\"hello\",\"ip\":\"127.0.0.1\",\"port\":60000}");
        when(commands.contains(anyString())).thenReturn(true);
        when(commands.getByName(anyString())).thenReturn(systemCommand);
        when(systemCommand.execute(any(BodyMessage.class))).thenReturn(Status.SUCCESS);

        messageHandler.handle(transportConnection);

        verify(transportConnection).receive();
        verify(transportConnection).getIp();
        verify(systemCommand).execute(any(BodyMessage.class));
        verify(transportConnection).send(anyString());
    }
}
