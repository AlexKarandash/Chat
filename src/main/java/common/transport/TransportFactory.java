package common.transport;

import java.io.IOException;

//интерфейс работы c траноспортным уровнем
public interface TransportFactory {
    TransportListener createListener(int port, int maxCountConnections, String encoding) throws IOException;

    TransportConnection createConnection(String ip, int port, String encoding) throws IOException;
}
