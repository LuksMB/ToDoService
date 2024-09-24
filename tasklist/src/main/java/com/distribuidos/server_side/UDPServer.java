// UDPServer.java
package com.distribuidos.server_side;

import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.distribuidos.models.Mensagem;
import com.distribuidos.models.RequestInfo;

public class UDPServer {
    private DatagramSocket socket = null;
    private Dispatcher despachante = null;

    // Histórico de requisições processadas
    private Map<RequestInfo, String> requestHistory = new HashMap<>();

    public UDPServer(int port) {
        try {
            this.socket = new DatagramSocket(port);
            this.despachante = new Dispatcher();
            System.out.println("Servidor aguardando conexões na porta " + port);
        } catch (SocketException e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }

    public void start() {
        try {
            while (true) {
                DatagramPacket request = getRequest();
                String receivedData = new String(request.getData(), 0, request.getLength());

                // Captura de endereço para histórico
                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();

                // Extrair id da requisição
                Mensagem receivedMsg = Mensagem.desempacotarMensagem(receivedData);
                int requestId = receivedMsg.getId();
                RequestInfo clientInfo = new RequestInfo(requestId, clientAddress, clientPort);

                // Verificar se a requisição já foi processada
                if (requestHistory.containsKey(clientInfo)) {
                    System.out.println("Requisição duplicada detectada. Enviando resposta do cache.");
                    sendReply(requestHistory.get(clientInfo), clientAddress, clientPort);
                } else {
                    String responseJson = despachante.dispatch(receivedData);
                    requestHistory.put(clientInfo, responseJson);
                    // sendReply(responseJson, request.getAddress(), request.getPort());
                }
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
            System.err.println("Erro ao enviar resposta: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer(6789);
        server.start();
    }
}
