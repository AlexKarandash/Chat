package common.json.bodymessage;

import common.json.BaseJsonSerializer;
import common.json.jsonSerializer;

//реализация Serializer через BodyMessage
public class BodyMessageJsonSerializer extends BaseJsonSerializer<BodyMessage> implements jsonSerializer<BodyMessage> {

    public BodyMessageJsonSerializer() {
        super(BodyMessage.class);
    }
}
