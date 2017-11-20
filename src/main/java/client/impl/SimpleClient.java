package client.impl;

import client.Client;
import common.listener.Listener;
import client.sender.MessageSender;
import common.enums.CommandMessage;
import common.json.MessageFactory;
import common.stream.StreamIO;
import common.json.bodymessage.BodyMessage;

import java.io.*;

public class SimpleClient implements Client {

    StreamIO streamIO; //интерфейс для работы с вводом-выводом
    Listener listener; //слушатель входяших сообщений в потоке
    MessageSender messageSender; //отправщик сообщений
    boolean stopped = false; //остановлен ли клиент

    //параметры подключений клиента
    int portClient;
    String nickName;
    ///

    public SimpleClient(int portClient, MessageSender messageSender, StreamIO streamIO, Listener listener) {
        this.portClient = portClient;
        this.streamIO = streamIO;
        this.messageSender = messageSender;
        this.listener = listener;
    }

    //стартовать работу клиента
    public void start() {
        try {
            streamIO.print("Добро пожаловать в чат");
            startListener(); //запуск слушателя

            //читаем ввод пользователя
            String message;
            while (true) {
                message = streamIO.read();
                if ((stopped) || (message.equals(CommandMessage.EXIT.getTextCommand()))) break;
                sendMessage(message);
            }

            logoutAndStop(); //выход
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Разрыв соединения с сервером");
        }
    }

    //авторизация
    public boolean login(String nickName) throws IOException {
        this.nickName = nickName;
        BodyMessage bodyMessage = MessageFactory.createBodyMessage(CommandMessage.LOGIN.getTextCommand(),
                nickName, String.valueOf(portClient), "", portClient);
        return messageSender.sendMessage(bodyMessage);
    }

    //выход из чата и остановка
    public void logoutAndStop() throws IOException {
        stopped = true;
        sendMessage(CommandMessage.EXIT.getTextCommand());
        listener.interrupt();
    }

    //отправить сообщение клиентом
    public void sendMessage(String message) throws IOException {
        BodyMessage bodyMessage = MessageFactory.createBodyMessage(CommandMessage.MESSAGE.getTextCommand(),
                nickName, message, "", portClient);
        sendBodyMessage(bodyMessage);
    }

    //отправить сообщение-объектом клиентом
    private void sendBodyMessage(BodyMessage bodyMessage) throws IOException {
        if (!messageSender.sendMessage(bodyMessage)) {
            System.err.println("Сообщение не обработалось на сервере");
        }
    }

    //запускаем слушателя входных сообщений
    public void startListener() {
        listener.setDaemon(true);
        listener.start();
    }
}
