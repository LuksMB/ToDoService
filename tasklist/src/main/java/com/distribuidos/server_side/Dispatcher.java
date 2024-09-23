package com.distribuidos.server_side;

import com.distribuidos.models.AddTaskRequestParams;
import com.distribuidos.models.CompleteTaskRequestParams;
import com.distribuidos.models.Mensagem;
import com.distribuidos.models.RemoveTaskRequestParams;
import com.distribuidos.models.ViewAllTaskRequestParams;
import com.distribuidos.models.ViewTaskRequestParams;

public class Dispatcher {
    private Skeleton skeleton;

    public Dispatcher() {
        skeleton = new Skeleton();
    }

    public String dispatch(String receivedData) {
        try {
            Mensagem msg = Mensagem.desempacotarMensagem(receivedData);
            String serviceName = msg.getObjectReference();
            String methodName = msg.getMethodId();
            String arguments = msg.getArguments();

            switch (methodName) {
                case "addTask":   
                    return skeleton.addTask(AddTaskRequestParams.empacotar(new AddTaskRequestParams(serviceName, methodName, arguments, msg.getId())));
                case "viewTask":
                    return skeleton.viewTask(ViewTaskRequestParams.empacotar(new ViewTaskRequestParams(serviceName, methodName, arguments, msg.getId())));
                case "removeTask":
                    return skeleton.removeTask(RemoveTaskRequestParams.empacotar(new RemoveTaskRequestParams(serviceName, methodName, arguments, msg.getId())));
                case "viewAllTasks":
                    return skeleton.viewAllTasks(ViewAllTaskRequestParams.empacotar(new ViewAllTaskRequestParams(serviceName, methodName, arguments, msg.getId())));
                case "completeTask":
                    return skeleton.completeTask(CompleteTaskRequestParams.empacotar(new CompleteTaskRequestParams(serviceName, methodName, arguments, msg.getId())));
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