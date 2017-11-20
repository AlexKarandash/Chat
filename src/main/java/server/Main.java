package server;

import common.input.InputWorker;
import common.stream.ConsoleStreamIO;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;
import server.impl.SimpleServer;

import java.util.Scanner;

public class Main {

    //запуск сервера
    public static void main(String[] args) {
        StreamIO streamIO = new ConsoleStreamIO(new Scanner((System.in)), System.out);
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);
        int port = inputWorker.getPortServer();
        Server server = new SimpleServer(port, streamIO, transportFactory);
        server.start();
    }
}
