package bot;

import common.stream.ConsoleStreamIO;
import common.stream.StreamIO;
import common.transport.TransportFactory;
import common.transport.tcp.TcpSocketTransportFactory;

import java.util.*;

public class Main {

    private static void printLogAndWait(StreamIO streamIO, String text, int waitTime) {
        streamIO.print(text);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //запуск бота
    public static void main(String[] args) {

        //инициализируем параметры бота
        StreamIO streamIO = new ConsoleStreamIO(new Scanner((System.in)), System.out);
        TransportFactory transportFactory = new TcpSocketTransportFactory();
        BotWorker BotWorker = new SimpleBotWorker(streamIO, transportFactory);
        BotService botService = BotService.getInstance();

        //добавляем в чат пользователей
        BotWorker.AddBots(botService.getIpServer(), botService.getPortServer(),
                botService.getStartPort(), botService.getCountUser(), botService.getIntervalConnect());
        printLogAndWait(streamIO, "STOP LOGIN - START SEND", 3000);

        //запуск по таймеру отправку сообщений пользователями
        BotWorker.startSendMessage(botService.getTimerSendMessage(), botService.getIntervalConnect());
        printLogAndWait(streamIO, "STOP SEND - START LOGOUT", 5000);

        //Пользователи выходят из чата
        BotWorker.removeBots(botService.getIntervalConnect());
        printLogAndWait(streamIO, "STOP LOGOUT BOT", 5000);

        System.exit(0);
    }
}

