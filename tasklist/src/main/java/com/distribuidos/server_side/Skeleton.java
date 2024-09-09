package com.distribuidos.server_side;

import com.distribuidos.models.AddTaskRequest;
import com.distribuidos.models.Task;
import com.distribuidos.models.TaskList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

    public String viewTask(int index) {
        Task task = taskList.viewTask(index);
        if (task != null) {
            return task.serializar();
        } else {
            return "Tarefa não encontrada.";
        }
    }

    public String viewAllTasks() {
        Task[] tasks = taskList.viewAllTasks();
        try {
            return objectMapper.writeValueAsString(tasks);
        } catch (Exception e) {
            return "Erro ao serializar tarefas: " + e.getMessage();
        }
    }

    public String removeTask(int index) {
        return taskList.removeTask(index);
    }

    public JsonNode processRequest(String methodId, JsonNode arguments) {
        ObjectNode response = objectMapper.createObjectNode();
        response.put("methodId", methodId);

        try {
            switch (methodId) {
                case "addTask":
                    AddTaskRequest addTaskRequest = objectMapper.treeToValue(arguments, AddTaskRequest.class);
                    response.put("result", addTask(addTaskRequest));
                    break;

                case "viewTask":
                    int viewIndex = arguments.get("index").asInt();
                    response.put("result", viewTask(viewIndex));
                    break;

                case "viewAllTasks":
                    response.set("result", objectMapper.readTree(viewAllTasks()));
                    break;

                case "removeTask":
                    int removeIndex = arguments.get("index").asInt();
                    response.put("result", removeTask(removeIndex));
                    break;

                default:
                    response.put("error", "Comando desconhecido!");
            }
        } catch (Exception e) {
            response.put("error", e.getMessage());
        }

        return response;
    }
}

