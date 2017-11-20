package common.input;

import common.Service;
import common.check.Check;
import common.check.CheckFactory;
import common.transport.TransportFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//класс для работы с вводом и проверкой порта
public class InputPort extends Input<Integer> {
    boolean checkIsUsed;
    TransportFactory transportFactory;

    protected Check getCheck(String value) {
        return CheckFactory.getPortCheck(value, Service.getInstance().getMinPort(),
                Service.getInstance().getMaxPort(), checkIsUsed, transportFactory);
    }

    public Integer getValue() {
        return Integer.parseInt(super.getValueAfterCheck());
    }
}
