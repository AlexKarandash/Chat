package server.handler;

//интерфейс обработки сообщений из очереди
public interface MessageHandler<T> {
    public void handle(T message);
}
