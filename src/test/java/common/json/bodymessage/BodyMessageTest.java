package common.json.bodymessage;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

//значение из сеттера должно равняться геттеру
public class BodyMessageTest {

    BodyMessage bodyMessage;

    @Before
    public void setUp() throws Exception {
        bodyMessage = new BodyMessage();
    }

    @Test
    public void testSetGetCommand() throws Exception {
        bodyMessage.setCommand("EXIT");

        Assert.assertEquals(bodyMessage.getCommand(), "EXIT");
    }

    @Test
    public void testSetGetNickName() throws Exception {
        bodyMessage.setNickName("Alex");

        Assert.assertEquals(bodyMessage.getNickName(), "Alex");
    }

    @Test
    public void testSetGetText() throws Exception {
        bodyMessage.setText("hello");

        Assert.assertEquals(bodyMessage.getText(), "hello");
    }

    @Test
    public void testSetGetIp() throws Exception {
        bodyMessage.setIp("127.0.0.1");

        Assert.assertEquals(bodyMessage.getIp(), "127.0.0.1");
    }

    @Test
    public void testSetGetPort() throws Exception {
        bodyMessage.setIp("60000");

        Assert.assertEquals(bodyMessage.getIp(), "60000");
    }
}
