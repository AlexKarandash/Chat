package common.input;

import common.check.Check;
import common.stream.StreamIO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//базовый класс для работы с вводом и проверкой значения
public abstract class Input<T> {

    String headerWelcomeMessage;
    String headerErrorMessage;
    StreamIO streamIO;

    //считать значение
    protected String getInputValue(String headerMessage) {
        streamIO.print(headerMessage);
        return streamIO.read();
    }

    //выполнить проверки и выташить значения
    protected String getValueAfterCheck() {
        String text = getInputValue(headerWelcomeMessage);
        while (true) {
            if (getCheck(text).check()) {
                break;
            }
            text = getInputValue(headerErrorMessage);
        }

        return text;
    }

    //получить свой класс проверки
    protected abstract Check getCheck(String text);

    //конечное типизированное значение
    public abstract T getValue();
}
