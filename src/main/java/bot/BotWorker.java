package bot;

public interface BotWorker {
    public void AddBots(String ipServer, int portServer, int startPort, int countBot, int intervalConnect);
    public void startSendMessage(int timerSendMessage, int intervalSend);
    public void removeBots(int intervalExit);
}
