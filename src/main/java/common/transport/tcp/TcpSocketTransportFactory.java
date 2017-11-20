package common.transport.tcp;

import common.transport.TransportConnection;
import common.transport.TransportFactory;
import common.transport.TransportListener;

import java.io.IOException;

public class TcpSocketTransportFactory implements TransportFactory {

    @Override
    public TransportListener createListener(int port, int maxCountConnections, String encoding) throws IOException {
        return new TcpSocketTransportListener(port, maxCountConnections, encoding);
    }

    @Override
    public TransportConnection createConnection(String ip, int port, String encoding) throws IOException {
        return new TcpSocketTransportConnection(ip, port, encoding);
    }
}
