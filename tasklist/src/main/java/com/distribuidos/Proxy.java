package com.distribuidos;

import com.distribuidos.models.AddTaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Proxy {

    private static int requestCounter = 0;

    public String addTask(AddTaskRequest request){

    }
    
    public Task viewTask(viewTaskRequest request){
        
    }

    public Task[] viewAllTasks(){

    }

    public String removeTask(RemoveTaskRequest request){
        
    }

    public String doOperation(String ServiceName, String methodName, String arguments){
        Proxy.requestCounter++;
        Mensagem msg = new Mensagem(0, Proxy.requestCounter, ServiceName, methodName, arguments);
        String serialized_msg = Mensagem.empacotarMensagem(msg);
        //TODO O BAGULHO DE UDP ENTRA AQUI ALEXSON
        String response = serialized_msg;
        Mensagem response_msg = Mensagem.desempacotarMensagem(response);
        return response_msg.getArguments();
    }
}