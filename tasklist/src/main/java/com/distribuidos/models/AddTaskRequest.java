package com.distribuidos.models;

public class AddTaskRequest {
    String titulo; 
    String descricao; 
    Boolean estado;

    public AddTaskRequest(String titulo, String descricao, Boolean estado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = estado;
    }
}
