package common.transport.tcp;

import common.Service;
import common.transport.TransportConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//реализация транспортого уровня через TCP Socket
public class TcpSocketTransportConnection implements TransportConnection {

    private Socket socket;
    private OutputStreamWriter out;
    private BufferedReader in;
    private String encoding;
    final String lineSeparator = Service.getInstance().getLineSeparator();

    public TcpSocketTransportConnection(Socket socket, String encoding) throws IOException {
        initSocket(socket, encoding);
    }

    public TcpSocketTransportConnection(String ip, int port, String encoding) throws IOException {
        socket = new Socket(ip, port);
        initSocket(socket, encoding);
    }

    private void initSocket(Socket socket, String encoding) throws IOException {
        this.socket = socket;
        this.encoding = encoding;
        initStream();
    }

    private void initStream() throws IOException {
        out = new OutputStreamWriter(socket.getOutputStream(), encoding);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), encoding));
    }

    @Override
    public String getIp() throws IOException {
        return socket.getInetAddress().getHostAddress();
    }

    @Override
    public String receive() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            stringBuilder.append(in.readLine());
            if (in.ready()) {
                stringBuilder.append(lineSeparator);
            } else {
                break;
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void send(String message) throws IOException {
        if (out != null) {
            out.write(message + lineSeparator); //чтобы сервер понял при чтении что это конец строки и отвис от readline
            out.flush();
        }
    }

    @Override
    public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
    }
}
