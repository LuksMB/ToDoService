package com.distribuidos.server_side;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.distribuidos.models.Task;

public class TaskList {
    private static final String FILE_NAME = "tasks.txt";

    private List<Task> readTasksFromFile() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.desserializar(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Arquivo não encontrado. Criando um novo arquivo...");
        }
        return tasks;
    }

    private String writeTasksToFile(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task task : tasks) {
                writer.write(task.serializar());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao manipular tarefas.";
        }
        return "Operação realizada com sucesso.";
    }

    public String addTask(String title, String description, boolean state) {
        Task task = new Task(title, description, state);
        List<Task> tasks = readTasksFromFile();
        tasks.add(task);
        return writeTasksToFile(tasks);
    }

    public Task[] viewAllTasks() {
        List<Task> tasks = readTasksFromFile();
        return tasks.toArray(new Task[0]);
    }

    public Task viewTask(String index) {
        int id = Integer.valueOf(index);
        List<Task> tasks = readTasksFromFile();
        if (id >= 0 && id < tasks.size()) {
            return tasks.get(id);
        } else {
            return null;
        }
    }

    public String removeTask(String index) {
        int id = Integer.valueOf(index);
        List<Task> tasks = readTasksFromFile();
        if (id >= 0 && id < tasks.size()) {
            Task removedTask = tasks.remove(id);
            writeTasksToFile(tasks);
            return "Tarefa removida com sucesso: " + removedTask.getTitle();
        } else {
            return "Tarefa não encontrada.";
        }
    }

    public String completeTask(String index) {
        int id = Integer.valueOf(index);
        List<Task> tasks = readTasksFromFile();
    
        if (id >= 0 && id < tasks.size()) { 
            Task task = tasks.get(id);
            task.setState(true);
            writeTasksToFile(tasks);
            return "Tarefa marcada como concluída: " + task.getTitle();
        } else {
            return "Tarefa não encontrada.";
        }
    }
}
