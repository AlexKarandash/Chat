package common.input;

import common.check.Check;
import common.check.CheckFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//класс для работы с вводом и проверкой IP
public class InputIp extends Input<String> {

    protected Check getCheck(String value) {
        return CheckFactory.getIpCheck(value);
    }

    public String getValue() {
        return super.getValueAfterCheck();
    }
}
