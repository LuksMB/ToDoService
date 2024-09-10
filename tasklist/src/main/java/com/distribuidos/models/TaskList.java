package com.distribuidos.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private static final String FILE_NAME = "tasks.txt";

    public String addTask(String title, String description, boolean state) {
        Task task = new Task(title, description, state);
        List<Task> tasks = readTasksFromFile();
        tasks.add(task);
        return writeTasksToFile(tasks);
    }

    public Task viewTask(int index) {
        List<Task> tasks = readTasksFromFile();
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        }
        return null;
    }

    public Task[] viewAllTasks() {
        List<Task> tasks = readTasksFromFile();
        return tasks.toArray(new Task[0]);
    }

    public String removeTask(int index) {
        List<Task> tasks = readTasksFromFile();
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            return writeTasksToFile(tasks);
        }
        return "Tarefa não encontrada.";
    }

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
}
