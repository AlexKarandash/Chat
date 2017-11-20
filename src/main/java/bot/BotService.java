package bot;

import common.BaseService;
import lombok.Getter;

@Getter
public class BotService extends BaseService<BotService> {

    private static BotService instance;

    private String ipServer;
    private int portServer;
    private int countUser;
    private int startPort;
    private int intervalConnect;
    private int timerSendMessage;

    private BotService() {
        super("src/main/resources/bot.properties");

        ipServer = properties.getProperty("ipServer");
        portServer = Integer.parseInt(properties.getProperty("portServer"));
        countUser = Integer.parseInt(properties.getProperty("countUser"));
        startPort = Integer.parseInt(properties.getProperty("startPort"));
        intervalConnect = Integer.parseInt(properties.getProperty("intervalConnect"));
        timerSendMessage = Integer.parseInt(properties.getProperty("timerSendMessage"));
    }

    public static BotService getInstance() {
        if (instance == null) {
            instance = new BotService();
        }
        return instance;
    }
}
