package bot;

import client.Client;
import client.impl.SimpleClient;
import client.impl.SimpleClientListener;
import client.sender.MessageSender;
import client.sender.SimpleMessageSender;
import common.check.CheckFactory;
import common.listener.Listener;
import common.stream.StreamIO;
import common.transport.TransportFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class SimpleBotWorker implements BotWorker{

    private StreamIO streamIO;
    private TransportFactory transportFactory;
    List<Client> clients;

    public SimpleBotWorker(StreamIO streamIO, TransportFactory transportFactory) {
        this.streamIO = streamIO;
        this.transportFactory = transportFactory;
        clients = new ArrayList<Client>();
    }

    //добавить countBot ботов к серверу  ipServer:portServer в интервал intervalConnect
    public void AddBots(String ipServer, int portServer, int startPort, int countBot, int intervalConnect){
        for (int i = 1; i <= countBot; i++) {
            try {
                Thread.sleep(intervalConnect);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int port = startPort + i;
            if (CheckFactory.PortIsNotUsedCheck(String.valueOf(port), transportFactory).check()) {
                MessageSender messageSender = new SimpleMessageSender(transportFactory, ipServer, portServer);
                Listener listener = new SimpleClientListener(port, streamIO, transportFactory);
                Client client = new SimpleClient(port, messageSender, streamIO, listener);
                try {
                    if (client.login(String.format("User_%s", i))) {
                        client.startListener();
                        clients.add(client);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //запуск по таймеру отправки сообщений пользователями каждые  intervalConnect милисекунд
    //в течение timerSendMessage милисекунд
    public void startSendMessage(int timerSendMessage, int intervalSend){
        BotTimerTask botTimerTask = new BotTimerTask();
        botTimerTask.setClients(clients);
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(botTimerTask, 0, intervalSend);
        try {
            Thread.sleep(timerSendMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }

    //удаляем ботов, каждый выходит из чата через intervalExit милисекунд
    public void removeBots(int intervalExit){
        //Пользователи выходят из чата
        for (Client client : clients) {
            try {
                Thread.sleep(intervalExit);
                client.logoutAndStop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
