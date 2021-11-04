package ru.nsu.sergomyaso.multy.core;

import ru.nsu.sergomyaso.multy.context.NetworkContext;
import ru.nsu.sergomyaso.multy.publisher.MulticastPublisher;
import ru.nsu.sergomyaso.multy.reciever.MulticastReceiver;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class MulticastSocketFactory {

    public InetSocketAddress getGroupAddress(NetworkContext context) {
        try {
            return new InetSocketAddress(InetAddress.getByName(context.getIpGroup()), context.getPort());
        } catch (UnknownHostException exception) {
            exception.printStackTrace();
            System.out.println("[ERROR] Unknown host");
            return null;
        }
    }

    public MulticastSocket getMulticastSocket(NetworkContext context, InetSocketAddress group) {

        try {
            MulticastSocket socket = new MulticastSocket(context.getPort());
            socket.joinGroup(group, null);
            return socket;
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("[ERROR] Creating or joining socket error");
            return null;
        }
    }

    public MulticastPublisher getMulticastPublisher(NetworkContext context) {
        var group = getGroupAddress(context);
        var socket = getMulticastSocket(context, group);
        return new MulticastPublisher(socket, group);
    }

    public MulticastThreadNotifier getMulticastNotifier(NetworkContext context, String message, int notifyPeriod) {
        var publisher = getMulticastPublisher(context);
        return new MulticastThreadNotifier(publisher, message, notifyPeriod);
    }

    public MulticastReceiver getMulticastReceiver(NetworkContext context, int bufferSize) {
        var group = getGroupAddress(context);
        var socket = getMulticastSocket(context, group);
        return new MulticastReceiver(socket, bufferSize);
    }

}
