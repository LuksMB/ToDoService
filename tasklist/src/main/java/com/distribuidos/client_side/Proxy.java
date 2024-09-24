package com.distribuidos.client_side;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import com.distribuidos.models.AddTaskRequest;
import com.distribuidos.models.CompleteTaskRequest;
import com.distribuidos.models.Mensagem;
import com.distribuidos.models.RemoveTaskRequest;
import com.distribuidos.models.Task;
import com.distribuidos.models.ViewTaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Proxy {

    private static int requestCounter = 0;

    public String addTask(AddTaskRequest request) throws Exception{
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(request);
            String response = doOperation("TaskService", "addTask", requestJson);
            return response;

        } catch (Exception e) {
            return null;
        } 
    }

    public Task[] viewAllTasks() throws Exception{
        try {
            String retorno = doOperation("TaskService", "viewAllTasks", "");
            if (retorno == null){
                throw new Exception();
            }
            Task[] tasks = Task.stringToTaskArray(retorno);
            return tasks;

        } catch (Exception e) {
            return null;
        }
    }
    
    public Task viewTask(ViewTaskRequest request){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(request);
            String resposta = doOperation("TaskService", "viewTask", requestJson);
            if (resposta == null){
                throw new SocketException();
            }
            return Task.desserializar(resposta);
        } catch (Exception e) {
            return null;
        }
    }

    public String removeTask(RemoveTaskRequest request){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(request);
            String resposta = doOperation("TaskService", "removeTask", requestJson);
            if (resposta == null){
                throw new SocketException();
            }
            return resposta;

        } catch (Exception e) {
            return null;
        }
    }

    public String completeTask(CompleteTaskRequest request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(request);
            String resposta =  doOperation("TaskService", "completeTask", requestJson);
            if (resposta == null){
                throw new SocketException();
            }
            return resposta;
        } catch (Exception e) {
            return null;
        }
    }

    public String doOperation(String ServiceName, String methodName, String arguments) throws SocketException{
        
        DatagramSocket aSocket = new DatagramSocket();
        aSocket.setSoTimeout(1500);
        Proxy.requestCounter++;
        int maxRetries = 3;
        
        Mensagem msg = new Mensagem(0, Proxy.requestCounter, ServiceName, methodName, arguments);
        String serialized_msg = Mensagem.empacotarMensagem(msg);
        String response = "";

        for (int attempt = 0; attempt < maxRetries; attempt++) {
                try {
                    UDPClient.send(aSocket, serialized_msg);
                    response = UDPClient.receive(aSocket);
                    Mensagem response_msg = Mensagem.desempacotarMensagem(response);
                    return response_msg.getArguments();

                } catch (Exception e) {
                    System.out.println("Timeout(" + (attempt + 1) + "/" + maxRetries + ")");
                }
        }
        if (aSocket != null) {
            aSocket.close();
        }
        return null;       
    }
}