package com.distribuidos.models;

public class AddTaskRequest {
    String titulo; 
    String descricao; 
    Boolean estado;

    public AddTaskRequest() {}
    
    public AddTaskRequest(String titulo, String descricao, Boolean estado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "AddTaskRequest{" +
                "titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", estado=" + estado +
                '}';
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getTitulo() {
        return titulo;
    }
}
