package common.input;

import common.stream.StreamIO;
import common.transport.TransportFactory;

//инициализация ввода данных
public class InputWorker {

    private StreamIO streamIO;
    private TransportFactory transportFactory;

    public InputWorker(StreamIO streamIO, TransportFactory transportFactory){
        this.streamIO = streamIO;
        this.transportFactory = transportFactory;
    }

    public int getPortServer(){
        return getPort("Input Server port", "Port is incorrect or used. Try again", true);
    }

    public String getIpServer(){
        return getIp("Введите IP адрес сервера", "IP адрес введен неверно. Повторите попытку");
    }

    public int getPortToServer(){
        return getPort("Введите порт для соединения с сервером", "Порт введен неверно. Повторите попытку", false);
    }

    public int getPortClient(){
        return getPort("Введите порт для входящих сообщений",
                "Порт введен неверно или уже используется. Повторите попытку", true);
    }

    public String getNickName(String welcomeMessage){
        streamIO.print(welcomeMessage);
        return streamIO.read();
    }

    private int getPort(String welcomeMessage, String errorMessage, Boolean checkIsUsed){
        InputPort inputPort = new InputPort();
        inputPort.setHeaderWelcomeMessage(welcomeMessage);
        inputPort.setHeaderErrorMessage(errorMessage);
        inputPort.setCheckIsUsed(checkIsUsed);
        inputPort.setTransportFactory(transportFactory);
        inputPort.setStreamIO(streamIO);
        return inputPort.getValue();
    }

    private String getIp(String welcomeMessage, String errorMessage){
        InputIp inputIp = new InputIp();
        inputIp.setHeaderWelcomeMessage(welcomeMessage);
        inputIp.setHeaderErrorMessage(errorMessage);
        inputIp.setStreamIO(streamIO);
        return inputIp.getValue();
    }
}
