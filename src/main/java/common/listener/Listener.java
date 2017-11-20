package common.listener;

//интерфейс слушателя
public interface Listener {
    void start();

    void interrupt();

    void setDaemon(boolean on);
}
