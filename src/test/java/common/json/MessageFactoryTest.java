package common.json;

import common.json.bodymessage.BodyMessage;
import junit.framework.Assert;
import org.junit.Test;

public class MessageFactoryTest {

    @Test
    //результат createBodyMessage не должен быть пустым
    public void testCreateBodyMessage_Create_NotNullReturned() throws Exception {
        BodyMessage bodyMessage = MessageFactory.createBodyMessage("MESSAGE", "alex", "hello", "127.0.0.1", 60000);

        Assert.assertNotNull(bodyMessage);
    }
}
