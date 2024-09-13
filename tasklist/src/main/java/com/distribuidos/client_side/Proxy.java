package com.distribuidos.client_side;

import com.distribuidos.models.AddTaskRequest;
import com.distribuidos.models.CompleteTaskRequest;
import com.distribuidos.models.Mensagem;
import com.distribuidos.models.RemoveTaskRequest;
import com.distribuidos.models.Task;
import com.distribuidos.models.ViewTaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Proxy {

    private static int requestCounter = 0;

    public String addTask(AddTaskRequest request){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(request);
            return doOperation("TaskService", "addTask", requestJson);

        } catch (Exception e) {
            System.out.println("Erro ao serializar a requisição: " + e.getMessage());
        }
        return null;
    }

    public Task[] viewAllTasks(){
        try {
            String retorno = doOperation("TaskService", "viewAllTasks", "");
            Task[] tasks = Task.stringToTaskArray(retorno);
            return tasks;

        } catch (Exception e) {
            System.out.println("Erro ao serializar a requisição: " + e.getMessage());
        }
        return null;
    }
    
    public Task viewTask(ViewTaskRequest request){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(request);
            return Task.desserializar(doOperation("TaskService", "viewTask", requestJson));

        } catch (Exception e) {
            System.out.println("Erro ao serializar a requisição: " + e.getMessage());
        }
        return null;
    }

    public String removeTask(RemoveTaskRequest request){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(request);
            return doOperation("TaskService", "removeTask", requestJson);

        } catch (Exception e) {
            System.out.println("Erro ao serializar a requisição: " + e.getMessage());
        }
        return null;
    }

    public String completeTask(CompleteTaskRequest request) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String requestJson = objectMapper.writeValueAsString(request);
            return doOperation("TaskService", "completeTask", requestJson);
        } catch (Exception e) {
            System.out.println("Error ao serializar a requisição: " + e.getMessage());
        }
        return null;
    }

    public String doOperation(String ServiceName, String methodName, String arguments){
        Proxy.requestCounter++;
        Mensagem msg = new Mensagem(0, Proxy.requestCounter, ServiceName, methodName, arguments);
        String serialized_msg = Mensagem.empacotarMensagem(msg);
        String response = UDPClient.send(serialized_msg);
        Mensagem response_msg = Mensagem.desempacotarMensagem(response);
        return response_msg.getArguments();
    }
}