package server.handler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Очередь сообщений для обработки
public class LinkedListMessageQueue<T> implements MessageQueue<T> {

    private LinkedList<T> queue = new LinkedList();
    private int minThreads;
    private int maxThreads;
    private int currentThreads = 0;
    private List<HandleThread> threadPool = new ArrayList();
    private boolean running = true;
    private MessageHandler<T> messageHandler;

    public LinkedListMessageQueue(int minThreads, int maxThreads, MessageHandler<T> messageHandler) {
        // инициализируем параметры
        this.minThreads = minThreads;
        this.maxThreads = maxThreads;
        this.messageHandler = messageHandler;
        currentThreads = this.minThreads;

        //создание пула потоков
        for (int i = 0; i < this.minThreads; i++) {
            createHandleThread();
        }
    }

    //добавить запрос в обработку
    public synchronized void add(T message) {
        //добавить сообщение в очередь
        queue.addLast(message);

        //ищем свободный поток для обработки запросов
        boolean availableThread = false;
        for (HandleThread handleThread : threadPool) {
            if (!handleThread.isProcessing()) {
                availableThread = true;
                break;
            }
        }
        //Если не нашелся свободнй поток создадим новый
        if (!availableThread) {
            if (currentThreads < this.maxThreads) {
                createHandleThread();
            }
        }

        //разбудить спящие потоки
        notifyAll();
    }

    private void createHandleThread() {
        HandleThread thread = new SimpleHandleThread(this, messageHandler);
        thread.start();
        threadPool.add(thread);
    }

    //достать запрос из очереди
    public synchronized T getNextObject() {
        //Если очередь пустая усыпить поток
        while (queue.isEmpty()) {
            try {
                if (!running) {
                    return null;
                }
                wait();
            } catch (InterruptedException ignored) {
            }
        }

        // достать элемент из очереди
        return queue.removeFirst();
    }

    //остановить обработку очереди
    public synchronized void shutdown() {
        running = false;

        for (HandleThread handleThread : threadPool) {
            handleThread.killThread();
        }
        threadPool.clear();

        //разбудить спящие потоки
        notifyAll();
    }

    //получить кол-во потоков в пуле
    public int getCountThreads(){
        return threadPool.size();
    }

}
