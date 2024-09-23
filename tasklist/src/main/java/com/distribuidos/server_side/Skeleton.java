package com.distribuidos.server_side;

import com.distribuidos.models.AddTaskRequest;
import com.distribuidos.models.AddTaskRequestParams;
import com.distribuidos.models.CompleteTaskRequest;
import com.distribuidos.models.CompleteTaskRequestParams;
import com.distribuidos.models.Mensagem;
import com.distribuidos.models.RemoveTaskRequest;
import com.distribuidos.models.RemoveTaskRequestParams;
import com.distribuidos.models.Task;
import com.distribuidos.models.ViewAllTaskRequestParams;
import com.distribuidos.models.ViewTaskRequest;
import com.distribuidos.models.ViewTaskRequestParams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Skeleton {

    private TaskList taskList;
    private ObjectMapper objectMapper;

    public Skeleton() {
        taskList = new TaskList();
        objectMapper = new ObjectMapper();
    }

    public String addTask(String args) {
        AddTaskRequestParams addTaskRequestParams = AddTaskRequestParams.desempacotar(args);
        AddTaskRequest addTaskRequest = null; 
        try {
           addTaskRequest = objectMapper.readValue(addTaskRequestParams.getArguments(), AddTaskRequest.class); 
        } catch (JsonProcessingException e) {
            System.out.println("Erro");
        }
        String response = taskList.addTask(addTaskRequest.getTitulo(), addTaskRequest.getDescricao(), addTaskRequest.getEstado());
        return Mensagem.empacotarMensagem(new Mensagem(0, addTaskRequestParams.getId(), addTaskRequestParams.getServiceName(), addTaskRequestParams.getMethodName(), response));
    }

    public String viewAllTasks(String args) {
        ViewAllTaskRequestParams viewAllTaskRequestParams = ViewAllTaskRequestParams.desempacotar(args);
        Task[] tasks = taskList.viewAllTasks();
        String response = null;
        try {
            response = objectMapper.writeValueAsString(tasks);
        } catch (Exception e) {
            return "Erro ao serializar tarefas: " + e.getMessage();
        }

        return Mensagem.empacotarMensagem(new Mensagem(0, viewAllTaskRequestParams.getId(), viewAllTaskRequestParams.getServiceName(), viewAllTaskRequestParams.getMethodName(), response));
    }

    public String viewTask(String args) throws Exception {
        ViewTaskRequestParams viewTaskRequestParams = ViewTaskRequestParams.desempacotar(args);
        ViewTaskRequest viewTaskRequest = null;
        try {
            viewTaskRequest = objectMapper.readValue(viewTaskRequestParams.getArguments(), ViewTaskRequest.class); 
        } catch (JsonProcessingException e) {
             System.out.println("Erro");
        }

        Task task = taskList.viewTask(viewTaskRequest.getIdRequest());
        String taskSerializada = null;
        if (task != null) {
            taskSerializada = task.serializar();
        } else {
            throw new Exception("Arquivo vazio");
        }

        if (taskSerializada != null) {
            return Mensagem.empacotarMensagem(new Mensagem(0, viewTaskRequestParams.getId(), viewTaskRequestParams.getServiceName(), viewTaskRequestParams.getMethodName(), taskSerializada));
        } else {
            return null;
        }
    }

    public String removeTask(String args) {
        RemoveTaskRequestParams removeTaskRequestParams = RemoveTaskRequestParams.desempacotar(args);
        RemoveTaskRequest removeTaskRequest = null;
        try {
            removeTaskRequest = objectMapper.readValue(removeTaskRequestParams.getArguments(), RemoveTaskRequest.class); 
        } catch (JsonProcessingException e) {
             System.out.println("Erro");
        }
        String response = taskList.removeTask(removeTaskRequest.getIdRequest());
        return Mensagem.empacotarMensagem(new Mensagem(0, removeTaskRequestParams.getId(), removeTaskRequestParams.getServiceName(), removeTaskRequestParams.getMethodName(), response));
    }

    public String completeTask(String args) {
        CompleteTaskRequestParams completeTaskRequestParams = CompleteTaskRequestParams.desempacotar(args);
        CompleteTaskRequest completeTaskRequest = null;
        try {
            completeTaskRequest = objectMapper.readValue(completeTaskRequestParams.getArguments(), CompleteTaskRequest.class); 
        } catch (JsonProcessingException e) {
             System.out.println("Erro");
        }

        String response = taskList.completeTask(completeTaskRequest.getIdRequest());
        return Mensagem.empacotarMensagem(new Mensagem(0, completeTaskRequestParams.getId(), completeTaskRequestParams.getServiceName(), completeTaskRequestParams.getMethodName(), response));
    }   
}

