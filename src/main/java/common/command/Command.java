package common.command;

import common.enums.Status;
import common.json.bodymessage.BodyMessage;

import java.io.IOException;

//интерфейс для работы с командами
public interface Command {
    public Status execute(BodyMessage bodyMessage) throws IOException;

    public String getName();

    public String getDescription();
}
