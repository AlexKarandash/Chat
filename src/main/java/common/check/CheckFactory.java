package common.check;

import common.transport.TransportFactory;

//фабрика для элементов проверки
public class CheckFactory {

    public static Check getBetweenNumberCheck(String value, int min, int max) {
        return new BetweenNumberCheck(value, min, max);
    }

    public static Check getIntegerCheck(String value) {
        return new IntegerCheck(value);
    }

    public static Check getIpCheck(String value) {
        return new IpCheck(value);
    }

    public static Check PortIsNotUsedCheck(String value, TransportFactory transportFactory) {
        return new PortIsNotUsedCheck(value, transportFactory);
    }

    public static Check getPortCheck(String value, int minPort, int maxPort, Boolean withCheckIsUsed, TransportFactory transportFactory) {
        CheckList portCheck = new CheckList();

        portCheck.add(getIntegerCheck(value));
        portCheck.add(getBetweenNumberCheck(value, minPort, maxPort));
        if (withCheckIsUsed) portCheck.add(PortIsNotUsedCheck(value, transportFactory));

        return portCheck;
    }

}
