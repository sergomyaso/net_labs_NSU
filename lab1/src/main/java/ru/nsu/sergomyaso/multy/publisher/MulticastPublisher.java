package ru.nsu.sergomyaso.multy.publisher;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class MulticastPublisher {
    private MulticastSocket socket;
    private DatagramPacket packet;
    private InetSocketAddress address;

    public MulticastPublisher(MulticastSocket socket, InetSocketAddress address){
        this.packet = new DatagramPacket("".getBytes(StandardCharsets.UTF_8), 0, address);
        this.socket = socket;
        this.address = address;
    }

    public InetSocketAddress getAddress() {
        return address;
    }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }

    public MulticastSocket getSocket() {
        return socket;
    }

    public void setSocket(MulticastSocket socket) {
        this.socket = socket;
    }

    public boolean publish(String message) {
        packet.setData(message.getBytes(StandardCharsets.UTF_8));
        packet.setLength(message.length());
        try {
            socket.send(packet);
            return true;
        } catch (IOException exception) {
            System.out.println("[ERROR] publish error");
            exception.printStackTrace();
            return false;
        }
    }

}