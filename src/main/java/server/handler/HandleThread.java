package server.handler;

//поток обрабатывающий сообщение из очереди
public interface HandleThread {
    public boolean isProcessing();

    public void killThread();

    public void start();
}
