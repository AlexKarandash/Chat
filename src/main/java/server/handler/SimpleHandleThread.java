package server.handler;

//реализация через обычный поток обработки сообщения
public class SimpleHandleThread<T> extends java.lang.Thread implements HandleThread {
    private MessageQueue<T> queue;
    private boolean processing = false;
    MessageHandler<T> messageHandler;

    public SimpleHandleThread(MessageQueue<T> queue, MessageHandler<T> messageHandler) {
        this.queue = queue;
        this.messageHandler = messageHandler;
    }

    //рабоатет ли поток
    public boolean isProcessing() {
        return this.processing;
    }

    //убить поток
    public void killThread() {
        this.interrupt();
    }

    //обработка потока
    public void run() {
        while (!isInterrupted()) {
            T message = queue.getNextObject();
            if (!isInterrupted() && message != null) {
                this.processing = true;
                messageHandler.handle(message); //обработка запроса потоком
                this.processing = false;
            }
        }
    }
}
