package com.distribuidos.server_side;

import com.distribuidos.models.AddTaskRequest;
import com.distribuidos.models.CompleteTaskRequest;
import com.distribuidos.models.Mensagem;
import com.distribuidos.models.RemoveTaskRequest;
import com.distribuidos.models.ViewTaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Dispatcher {
    private Skeleton skeleton;
    private ObjectMapper objectMapper;

    public Dispatcher() {
        skeleton = new Skeleton();
        objectMapper = new ObjectMapper();
    }

    public String dispatch(String receivedData) {
        try {
            Mensagem msg = Mensagem.desempacotarMensagem(receivedData);
            String serviceName = msg.getObjectReference();
            String methodName = msg.getMethodId();
            String arguments = msg.getArguments();
            String response = "";

            switch (methodName) {
                case "addTask":
                    AddTaskRequest addTaskRequest = objectMapper.readValue(arguments, AddTaskRequest.class);
                    response = skeleton.addTask(addTaskRequest);
                    return Mensagem.empacotarMensagem(new Mensagem(0, msg.getId(), serviceName, methodName, response));
                case "viewTask":
                    ViewTaskRequest viewRequest = objectMapper.readValue(arguments, ViewTaskRequest.class);
                    response = skeleton.viewTask(viewRequest);
                    return Mensagem.empacotarMensagem(new Mensagem(0, msg.getId(), serviceName, methodName, response));
                case "removeTask":
                    RemoveTaskRequest removeRequest = objectMapper.readValue(arguments, RemoveTaskRequest.class);
                    response = skeleton.removeTask(removeRequest);
                    return Mensagem.empacotarMensagem(new Mensagem(0, msg.getId(), serviceName, methodName, response));
                case "viewAllTasks":
                    response = skeleton.viewAllTasks();
                    return Mensagem.empacotarMensagem(new Mensagem(0, msg.getId(), serviceName, methodName, response));
                case "completeTask":
                    CompleteTaskRequest completeTaskRequest = objectMapper.readValue(arguments, CompleteTaskRequest.class);
                    response = skeleton.completeTask(completeTaskRequest);
                    return Mensagem.empacotarMensagem(new Mensagem(0, msg.getId(), serviceName, methodName, response));
                default:
                    System.out.println(serviceName);
                    return Mensagem.empacotarMensagem(new Mensagem(0, msg.getId(), serviceName, methodName, "Método desconhecido"));
            } 
        } catch (Exception e) {
            e.printStackTrace();
            return Mensagem.empacotarMensagem(new Mensagem(0, 0, "TaskService", "error", "Erro ao processar a requisição"));
        }
    }
    
}