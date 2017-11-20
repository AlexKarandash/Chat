package common.transport.tcp;

import common.transport.TransportConnection;
import common.transport.TransportListener;

import java.io.IOException;
import java.net.ServerSocket;

//реализация слушателя через TCP Socket
public class TcpSocketTransportListener implements TransportListener {

    private ServerSocket serverSocket;
    private String encoding;

    public TcpSocketTransportListener(int port, int maxCountConnections, String encoding) throws IOException {
        serverSocket = new ServerSocket(port, maxCountConnections);
        this.encoding = encoding;
    }

    @Override
    public TransportConnection accept() throws IOException {
        return new TcpSocketTransportConnection(serverSocket.accept(), encoding);
    }

    @Override
    public void close() throws IOException {
        serverSocket.close();
    }
}
