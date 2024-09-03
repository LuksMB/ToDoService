package com.distribuidos;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TaskList {
    public String addTask(String titulo, String descricao, Boolean estado){
        Task task = new Task(titulo, descricao, estado);

        String nomeArquivo = "tasks.ser";

        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo, true);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(task);

        } catch (IOException i) {
            i.printStackTrace();
            return "Erro ao adicionar a tarefa.";
        }

        return "Tarefa adicionada com sucesso.";
    }

    public Task viewTask(int id){
        
    }

    public Task[] viewAllTasks(){

    }

    public String removeTask(int id){
        
    }
}