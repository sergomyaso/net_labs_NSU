package ru.nsu.sergomyaso.multy.reciever;

import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MulticastReceiver {
    private MulticastSocket socket;
    private DatagramPacket packet;
    private byte[] buffer;
    private int bufferSize;

    public MulticastReceiver(MulticastSocket socket, int bufferSize) {
        this.buffer = new byte[bufferSize];
        this.packet = new DatagramPacket(buffer, bufferSize);
        this.socket = socket;
        this.bufferSize = bufferSize;
    }

    public String receive() {
        try {
            socket.receive(packet);
            return new String(buffer);
        } catch (IOException exception) {
            System.out.println("[ERROR] receive error");
            exception.printStackTrace();
            return "";
        }

    }

}
