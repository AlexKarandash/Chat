package common.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//проверка IP на корректность воода
public class IpCheck extends CheckBase implements Check {

    public IpCheck(String value) {
        super(value);
    }

    public boolean check() {
        Pattern p = Pattern.compile("((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)");
        Matcher m = p.matcher(value);
        return m.matches();
    }
}
