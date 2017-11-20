package server.handler;

import java.util.concurrent.*;

//Очередь сообщений для обработки  через ThreadPoolExecutor
public class ExecutorMessageQueue<T> implements MessageQueue<T> {

    ThreadPoolExecutor threadPoolExecutor;
    MessageHandler<T> messageHandler;

    public ExecutorMessageQueue(int minThreads, int maxThreads, MessageHandler<T> messageHandler) {
        this.messageHandler = messageHandler;
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        threadPoolExecutor = new ThreadPoolExecutor(minThreads, maxThreads, 1, TimeUnit.MINUTES, queue);
    }

    //добавить запрос в обработку
    public void add(T message) {
        threadPoolExecutor.execute(new MessageHandlerRunnable(message, messageHandler));
    }

    @Override
    public T getNextObject() {
        return null;
    }

    //остановить обработку очереди
    public void shutdown() {
        threadPoolExecutor.shutdown();
    }

    //получить кол-во потоков в пуле
    public int getCountThreads(){
        return threadPoolExecutor.getPoolSize();
    }
}
