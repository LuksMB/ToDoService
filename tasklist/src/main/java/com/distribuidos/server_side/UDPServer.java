package com.distribuidos.server_side;

import java.net.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class UDPServer {
    private DatagramSocket socket;
    private Dispatcher despachante;
    private ObjectMapper objectMapper;

    public UDPServer(int port) {
        try {
            socket = new DatagramSocket(port);
            despachante = new Dispatcher();
            objectMapper = new ObjectMapper();
            System.out.println("Servidor aguardando conexões na porta " + port);
        } catch (SocketException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }

    public void start() {
        try {
            while (true) {
                // Recebendo a requisição do cliente
                DatagramPacket request = getRequest();
                String receivedData = new String(request.getData(), 0, request.getLength());
                
                String responseJson = despachante.dispatch(receivedData);

                // Enviando resposta de volta ao cliente
                sendReply(responseJson, request.getAddress(), request.getPort());
            }
        } catch (IOException e) {
            System.out.println("Erro durante a comunicação: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public DatagramPacket getRequest() throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket request = new DatagramPacket(buffer, buffer.length);
        socket.receive(request);
        return request;
    }

    public void sendReply(String message, InetAddress clientAddress, int clientPort) {
        try {
            byte[] responseData = message.getBytes();
            DatagramPacket reply = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
            socket.send(reply);
        } catch (IOException e) {
            System.out.println("Erro ao enviar resposta: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer(6789);
        server.start();
    }
}