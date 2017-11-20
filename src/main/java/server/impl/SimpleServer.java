package server.impl;

import common.listener.Listener;
import common.command.*;
import common.Service;
import common.enums.CommandMessage;
import common.stream.StreamIO;
import common.transport.TransportConnection;
import common.transport.TransportFactory;
import server.Server;
import server.client.ChatClients;
import server.client.ConcurrentHashMapChatClients;
import server.handler.*;
import server.lastmessages.CircularFifoBufferLastMessages;
import server.lastmessages.LastMessages;
import common.json.bodymessage.BodyMessage;
import server.sender.MessageSender;
import server.sender.SimpleMessageSender;

public class SimpleServer implements Server {

    private LastMessages lastMessages; //список последних сообщений
    private ChatClients chatClients; // пользователи чата
    private StreamIO streamIO; //интерфейс для работы с вводом-выводом
    private boolean stopped = false; //остановлен ли сервер

    //входная и выходная очереди сервера
    private MessageQueue<TransportConnection> requestQueue;
    private MessageQueue<BodyMessage> responseQueue;

    //слушатель
    private Listener listener;

    public SimpleServer(int port, StreamIO streamIO, TransportFactory transportFactory) {
        this.streamIO = streamIO;
        Service service = Service.getInstance();

        //список пользователей
        chatClients = new ConcurrentHashMapChatClients();
        //очередь ответов
        /*responseQueue = new LinkedListMessageQueue<BodyMessage>(
                service.getMaxThreadsResponse(),
                service.getMaxThreadsResponse(),
                new ResponseHandler(chatClients, transportFactory));*/
        responseQueue = new ExecutorMessageQueue<BodyMessage>(
                service.getMaxThreadsResponse(),
                service.getMaxThreadsResponse(),
                new ResponseHandler(chatClients, transportFactory));
        //список последних сообщений
        lastMessages = new CircularFifoBufferLastMessages(service.getLastMessagesLength());
        //отправщки сообщений
        MessageSender messageSender = new SimpleMessageSender(lastMessages, responseQueue);
        //системные команды
        Commands systemCommands = getCommands(messageSender, chatClients);
        //очереь запросов
        /*requestQueue = new LinkedListMessageQueue<TransportConnection>(
                service.getMaxThreadsRequest(),
                service.getMaxThreadsRequest(),
                new RequestHandler(systemCommands));*/
        requestQueue = new ExecutorMessageQueue<TransportConnection>(
                service.getMaxThreadsRequest(),
                service.getMaxThreadsRequest(),
                new RequestHandler(systemCommands));
        //слушатель входящих сообщений
        listener = new SimpleServerListener(port, streamIO, transportFactory, requestQueue);
    }

    //инициализация пользовательских команд
    private Commands getCommands(MessageSender messageSender, ChatClients chatClients) {
        Commands clientCommands = CommandFactory.getHashMapCommands();

        clientCommands.add(CommandFactory.getHelpCommand(clientCommands, messageSender, chatClients));
        clientCommands.add(CommandFactory.getCountUserCommand(messageSender, chatClients));
        clientCommands.add(CommandFactory.getExitCommand(messageSender, chatClients));

        Commands systemCommands = CommandFactory.getHashMapCommands();
        systemCommands.add(CommandFactory.getLoginCommand(messageSender, chatClients));
        systemCommands.add(CommandFactory.getMessageCommand(clientCommands, messageSender, chatClients));

        return systemCommands;
    }

    //стартовать сервер
    public void start() {
        streamIO.print("Server start");
        startListener();

        //читаем ввод администратора
        String message = "";
        while (!((stopped) || (message.equals(CommandMessage.EXIT.getTextCommand())))) {
            message = streamIO.read();
        }

        stop();
    }

    //остановить сервер
    public void stop() {
        stopped = true;
        listener.interrupt();
        closeAll();
        streamIO.print("Server stop");
    }

    //запустить ожидание подключений
    private void startListener() {
        listener.setDaemon(true);
        listener.start();
    }

    //Закрывает все потоки всех соединений а также серверный сокет
    private void closeAll() {
        try {
            requestQueue.shutdown();
            responseQueue.shutdown();
            lastMessages.clear();
            chatClients.clear();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error closed connect");
        }
    }

}
