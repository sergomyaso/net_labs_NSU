package ru.nsu.sergomyaso.multy.core;

import ru.nsu.sergomyaso.multy.publisher.MulticastPublisher;
import java.net.InetSocketAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MulticastThreadNotifier extends Thread {
    private static final Logger logger = LogManager.getLogger();

    private MulticastPublisher publisher;
    private String message;
    private InetSocketAddress address;
    private int notifyPeriod;

    public MulticastThreadNotifier(MulticastPublisher publisher, String message, int notifyPeriod) {
        this.publisher = publisher;
        this.message = message;
        this.notifyPeriod = notifyPeriod;
    }

    public MulticastPublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(MulticastPublisher publisher) {
        this.publisher = publisher;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }

    public int getNotifyPeriod() {
        return notifyPeriod;
    }

    public void setNotifyPeriod(int notifyPeriod) {
        this.notifyPeriod = notifyPeriod;
    }

    private void notifyGroup() {
        while (true) {
            try {
                sleep(notifyPeriod);
                publisher.publish(message);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
                System.out.println("[ERROR] notify group error");
            }
        }
    }

    @Override
    public void run() {
        notifyGroup();
    }
}
