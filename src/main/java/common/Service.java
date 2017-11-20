package common;

import lombok.Getter;

@Getter
public class Service extends BaseService<Service> {

    private static Service instance;

    private String helloMessageForAll;
    private String goodbayMessageForAll;
    private String lineSeparator;
    private int maxCountConnections;
    private int lastMessagesLength;
    private int maxThreadsResponse;
    private int maxThreadsRequest;

    private final int minPort = 1025;
    private final int maxPort = 65535;
    private final String encoding = "UTF-8";

    private Service() {
        super("src/main/resources/config.properties");

        helloMessageForAll = properties.getProperty("helloMessageForAll");
        goodbayMessageForAll = properties.getProperty("goodbayMessageForAll");
        maxCountConnections = Integer.parseInt(properties.getProperty("maxCountConnections"));
        lastMessagesLength = Integer.parseInt(properties.getProperty("lastMessagesLength"));
        maxThreadsResponse = Integer.parseInt(properties.getProperty("maxThreadsResponse"));
        maxThreadsRequest = Integer.parseInt(properties.getProperty("maxThreadsRequest"));
        lineSeparator = properties.getProperty("lineSeparator");
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
}
