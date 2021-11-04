package ru.nsu.sergomyaso.multy.parser;

import ru.nsu.sergomyaso.multy.context.NetworkContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyNetworkParser {
    private static final String IP_GROUP = "ip_group";
    private static final String PORT = "port";
    private static final String NOTIFY_PERIOD = "notify_period_seconds";

    private Properties loadProperties(String propertyFile) {
        System.out.println(propertyFile);
        try (InputStream is = new FileInputStream(propertyFile)) {
            var properties = new Properties();
            properties.load(is);
            return properties;
        } catch (IOException exception) {
            System.out.println("[ERROR] Property file error");
            exception.printStackTrace();
        }
        return null;
    }

    public NetworkContext getNetworkContext(String propertyFile) {
        var properties = loadProperties(propertyFile);
        String ipGroup = properties.getProperty(IP_GROUP);
        int port = Integer.parseInt(properties.getProperty(PORT));
        int notifyPeriod = Integer.parseInt(properties.getProperty(NOTIFY_PERIOD));
        return new NetworkContext(ipGroup, port, notifyPeriod);
    }

}
