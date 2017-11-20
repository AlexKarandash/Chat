package common.json.bodymessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BodyMessage {
    private String command;
    private String nickName;
    private String text;
    private String ip;
    private int port;
}
