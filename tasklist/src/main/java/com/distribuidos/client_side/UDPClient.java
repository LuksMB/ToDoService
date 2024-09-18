// UDPClient.java
package com.distribuidos.client_side;

import java.net.*;
import java.io.*;

public class UDPClient {

    public static String send(String jsonMessage) {
        DatagramSocket aSocket = null;
        int maxRetries = 3; // Número máximo de tentativas
        int timeout = 2000; // Timeout de 2 segundos
        try {
            aSocket = new DatagramSocket();
            aSocket.setSoTimeout(timeout); // Definindo o timeout

            byte[] msg = jsonMessage.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;

            DatagramPacket request = new DatagramPacket(msg, msg.length, aHost, serverPort);
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

            for (int attempt = 0; attempt < maxRetries; attempt++) {
                try {
                    aSocket.send(request);
                    aSocket.receive(reply);
                    return new String(reply.getData(), 0, reply.getLength());

                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout, tentativa " + (attempt + 1) + " de " + maxRetries);
                }
            }
            // Se chegar aqui, todas as tentativas deram errado
            System.out.println("Erro: Servidor não respondeu após " + maxRetries + " tentativas.");
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
