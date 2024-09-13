package com.distribuidos.server_side;

import com.distribuidos.models.AddTaskRequest;
import com.distribuidos.models.CompleteTaskRequest;
import com.distribuidos.models.RemoveTaskRequest;
import com.distribuidos.models.Task;
import com.distribuidos.models.ViewTaskRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Skeleton {

    private TaskList taskList;
    private ObjectMapper objectMapper;

    public Skeleton() {
        taskList = new TaskList();
        objectMapper = new ObjectMapper();
    }

    public String addTask(AddTaskRequest request) {
        return taskList.addTask(request.getTitulo(), request.getDescricao(), request.getEstado());
    }

    public String viewAllTasks() {
        Task[] tasks = taskList.viewAllTasks();
        try {
            return objectMapper.writeValueAsString(tasks);
        } catch (Exception e) {
            return "Erro ao serializar tarefas: " + e.getMessage();
        }
    }

    public String viewTask(ViewTaskRequest viewRequest) {
        Task task = taskList.viewTask(viewRequest.getIdRequest());
        if (task != null) {
            return task.serializar();
        } else {
            return "Tarefa não encontrada.";
        }
    }

    public String removeTask(RemoveTaskRequest removeRequest) {
        return taskList.removeTask(removeRequest.getIdRequest());
    }

    public String completeTask(CompleteTaskRequest completeTaskRequest) {
        return taskList.completeTask(completeTaskRequest.getIdRequest());
    }   
}

