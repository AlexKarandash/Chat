package common.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//проверка на целое неотрицательное число длиной от 4 до 5 сиволов
public class IntegerCheck extends CheckBase implements Check {

    public IntegerCheck(String value) {
        super(value);
    }

    public boolean check() {
        Pattern p = Pattern.compile("[0-9]{4,5}");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
