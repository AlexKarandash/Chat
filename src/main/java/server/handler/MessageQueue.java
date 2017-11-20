package server.handler;

//интерфейс очереди сообщений для обработки
public interface MessageQueue<T> {
    public void add(T message);

    public T getNextObject();

    public void shutdown();

    public int getCountThreads();
}
