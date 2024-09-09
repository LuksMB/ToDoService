package com.distribuidos.client_side;

import com.distribuidos.models.AddTaskRequest;
import com.distribuidos.models.Mensagem;
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
    
    // public Task viewTask(viewTaskRequest request){
        
    // }

    // public Task[] viewAllTasks(){

    // }

    // public String removeTask(RemoveTaskRequest request){
        
    // }

    public String doOperation(String ServiceName, String methodName, String arguments){
        Proxy.requestCounter++;
        Mensagem msg = new Mensagem(0, Proxy.requestCounter, ServiceName, methodName, arguments);
        String serialized_msg = Mensagem.empacotarMensagem(msg);
        //TODO O BAGULHO DE UDP ENTRA AQUI ALEXSON
        String response = UDPClient.send(serialized_msg);
        Mensagem response_msg = Mensagem.desempacotarMensagem(response);
        return response_msg.getArguments();
    }
}