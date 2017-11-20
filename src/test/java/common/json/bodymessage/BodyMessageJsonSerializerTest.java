package common.json.bodymessage;

import common.json.MessageFactory;
import common.json.jsonSerializer;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class BodyMessageJsonSerializerTest {

    private jsonSerializer<BodyMessage> jsonSerializer;
    private BodyMessage bodyMessage;

    @Before
    public void setUp() throws Exception {
        jsonSerializer = new BodyMessageJsonSerializer();
        bodyMessage = MessageFactory.createBodyMessage("MESSAGE", "alex", "hello", "127.0.0.1", 60000);
    }

    @Test
    public void testSerialize() throws Exception {
        String result = jsonSerializer.serialize(bodyMessage);
        Assert.assertEquals(result, "{\"command\":\"MESSAGE\",\"nickName\":\"alex\",\"text\":\"hello\",\"ip\":\"127.0.0.1\",\"port\":60000}");
    }

    @Test
    public void testDeserialize() throws Exception {
        String object = "{\"command\":\"MESSAGE\",\"nickName\":\"alex\",\"text\":\"hello\",\"ip\":\"127.0.0.1\",\"port\":60000}";

        BodyMessage bodyMessageNew = jsonSerializer.deserialize(object);

        Assert.assertEquals(bodyMessage.getCommand(), bodyMessageNew.getCommand());
        Assert.assertEquals(bodyMessage.getNickName(), bodyMessageNew.getNickName());
        Assert.assertEquals(bodyMessage.getIp(), bodyMessageNew.getIp());
        Assert.assertEquals(bodyMessage.getText(), bodyMessageNew.getText());
        Assert.assertEquals(bodyMessage.getPort(), bodyMessageNew.getPort());
    }
}
