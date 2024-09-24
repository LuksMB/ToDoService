// UDPClient.java
package com.distribuidos.client_side;

import java.net.*;
import java.io.*;

public class UDPClient {

    public static void send(DatagramSocket aSocket, String jsonMessage) throws SocketTimeoutException{
        
        try {
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;
            byte[] msg = jsonMessage.getBytes();
            DatagramPacket request = new DatagramPacket(msg, msg.length, aHost, serverPort);

            aSocket.send(request);
            
        } catch (SocketException e) {
            System.err.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO: " + e.getMessage());
        }
    }

    public static String receive(DatagramSocket aSocket){
        try {
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            aSocket.receive(reply);
            return new String(reply.getData(), 0, reply.getLength());

        } catch (IOException e) {
            return "null";
        }
    }
}
