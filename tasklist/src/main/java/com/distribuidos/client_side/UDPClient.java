package com.distribuidos.client_side;

import java.net.*;
import java.io.*;

public class UDPClient {

    public static String send(String jsonMessage) {
        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();

            byte[] msg = jsonMessage.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;

            DatagramPacket request = new DatagramPacket(msg, msg.length, aHost, serverPort);
            aSocket.send(request);

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);

            return new String(reply.getData());

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
        return null;
    }
}