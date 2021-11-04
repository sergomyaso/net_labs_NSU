package ru.nsu.sergomyaso.multy.core;

import ru.nsu.sergomyaso.multy.context.NetworkContext;
import ru.nsu.sergomyaso.multy.reciever.MulticastReceiver;

import java.util.*;

public class MulticastAppCore {
    private static final int UPLOAD_TIMER_DELAY = 0;
    private static final int MILLISECONDS_PERIOD_UPDATE = 3000;

    private MulticastSocketFactory factory;
    private MulticastThreadNotifier notifier;
    private MulticastReceiver receiver;
    private UUID appId;
    private HashMap<String, Boolean> appCopies;
    private Timer uploadTimer;

    public MulticastAppCore(MulticastSocketFactory factory, NetworkContext context) {
        uploadTimer = new Timer();
        appId = generateAppId();
        appCopies = new HashMap<>();
        this.factory = factory;
        notifier = factory.getMulticastNotifier(context, appId.toString(), context.getMillisecondsNotifyPeriod());
        receiver = factory.getMulticastReceiver(context, appId.toString().length());
    }

    private UUID generateAppId() {
        return UUID.randomUUID();
    }

    private boolean isValidAppId(String id) {
        try {
            UUID uuid = UUID.fromString(id);
            if (uuid.equals(appId))
                return false;
            return true;
        } catch (IllegalArgumentException exception) {
            System.out.println("[ERROR] not valid id:" + id);
            return false;
        }
    }

    private void showAppCopies() {
        if (!appCopies.values().removeAll(Collections.singleton(false))) {
            appCopies.keySet().forEach(id -> appCopies.put(id, false));
            return;
        }
        printConsoleMarkup();
        appCopies.keySet().forEach(id -> {
            appCopies.put(id, false);
            System.out.println(id);
        });
    }

    public void runTimer() {
        uploadTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                showAppCopies();
            }
        }, UPLOAD_TIMER_DELAY, MILLISECONDS_PERIOD_UPDATE);
    }

    private void printConsoleMarkup() {
        System.out.println("My id:" + appId);
        System.out.println("Copies:");
    }

    private void startFind() {
        printConsoleMarkup();
        while (true) {
            String receiveAppId = receiver.receive();
            if (isValidAppId(receiveAppId)) {
                if (!appCopies.containsKey(receiveAppId)) {
                    System.out.println(receiveAppId);
                }
                appCopies.put(receiveAppId, true);
            }
        }
    }

    public void runApp() {
        runTimer();
        notifier.start();
        startFind();
    }


}
