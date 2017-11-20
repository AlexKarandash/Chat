package common.check;

import common.Service;
import common.transport.TransportFactory;
import common.transport.TransportListener;

import java.io.IOException;

//проверить порт на занятость
public class PortIsNotUsedCheck extends CheckBase implements Check {

    TransportFactory transportFactory;

    public PortIsNotUsedCheck(String value, TransportFactory transportFactory) {
        super(value);
        this.transportFactory = transportFactory;
    }

    //проверить порт на используемость
    public boolean check() {
        int port = Integer.parseInt(value);
        boolean res = true;
        TransportListener transportListener = null;
        try {
            transportListener = transportFactory.createListener(port,
                    Service.getInstance().getMaxCountConnections(),
                    Service.getInstance().getEncoding());
        } catch (IOException e) {
            res = false;
        } finally {
            if (transportListener != null) {
                try {
                    transportListener.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

}
