package com.distribuidos.server_side;

import com.distribuidos.models.AddTaskRequest;
import com.distribuidos.models.Mensagem;
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
            // Desserializando a mensagem recebida
            Mensagem msg = Mensagem.desempacotarMensagem(receivedData);
            String serviceName = msg.getObjectReference();
            String methodName = msg.getMethodId();
            String arguments = msg.getArguments();
    

            switch (methodName) {
                case "addTask":
                    AddTaskRequest addTaskRequest = objectMapper.readValue(arguments, AddTaskRequest.class);
                    String response = skeleton.addTask(addTaskRequest);
                    return Mensagem.empacotarMensagem(new Mensagem(0, msg.getId(), serviceName, methodName, response));
                default:
                    return Mensagem.empacotarMensagem(new Mensagem(0, msg.getId(), serviceName, methodName, "Método desconhecido"));
            } 
        } catch (Exception e) {
            e.printStackTrace();
            return Mensagem.empacotarMensagem(new Mensagem(0, 0, "TaskService", "error", "Erro ao processar a requisição"));
        }
    }
    
}