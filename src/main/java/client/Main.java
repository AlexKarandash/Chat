package client;

import client.impl.SimpleClient;
import client.impl.SimpleClientListener;
import client.sender.MessageSender;
import client.sender.SimpleMessageSender;
import common.listener.Listener;
import common.Service;
import common.input.InputWorker;
import common.stream.ConsoleStreamIO;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    //запуск клиента
    public static void main(String[] args) {
        StreamIO streamIO = new ConsoleStreamIO(new Scanner(System.in), System.out);
        TransportFactory transportFactory = new TcpSocketTransportFactory();

        InputWorker inputWorker = new InputWorker(streamIO, transportFactory);
        String ipServer = inputWorker.getIpServer();
        int portToServer = inputWorker.getPortToServer();
        int portClient = inputWorker.getPortClient();

        MessageSender messageSender = new SimpleMessageSender(transportFactory, ipServer, portToServer);
        Listener listener = new SimpleClientListener(portClient, streamIO, transportFactory);
        //работа с клиентом
        try {
            Client client = new SimpleClient(portClient, messageSender, streamIO, listener);
            String nickName = inputWorker.getNickName("Введите свое имя");
            while (!client.login(nickName)) {
                nickName = inputWorker.getNickName("Это имя уже используется. Введите другое");
            }
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
