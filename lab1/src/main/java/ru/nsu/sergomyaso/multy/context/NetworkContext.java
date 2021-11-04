package ru.nsu.sergomyaso.multy.context;

public class NetworkContext {
    private String ipGroup;
    private int port;
    private int notifyPeriod; // in milliseconds

    public NetworkContext(String ipGroup, int port, int notifyPeriod) {
        this.ipGroup = ipGroup;
        this.port = port;
        this.notifyPeriod = notifyPeriod;
    }

    public String getIpGroup() {
        return ipGroup;
    }

    public void setIpGroup(String ipGroup) {
        this.ipGroup = ipGroup;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMillisecondsNotifyPeriod() {
        return notifyPeriod;
    }

    public void setMillisecondsNotifyPeriod(int notifyPeriod) {
        this.notifyPeriod = notifyPeriod;
    }
}
