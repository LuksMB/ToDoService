package com.distribuidos;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Proxy {

    public String empacotarMensagem(Mensagem msg){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(msg);   
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return json;
    }

    public Task desempacotarTask(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Task task = new Task();
        try {
            task = objectMapper.readValue(json, Task.class);
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return task;
    }

    public Mensagem desempacotarMensagem(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Mensagem msg = new Mensagem();
        try {
            msg = objectMapper.readValue(json, Mensagem.class);
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return msg;
    }

    private int requestCounter = 0;

    public String addTask(String titulo, String descricao, Boolean estado){
        
    }
    
    public Task viewTask(int id){
        
    }

    public Task[] viewAllTasks(){

    }

    public String removeTask(int id){

    }

    public String doOperation(String json){
        
    }

    public int getRequestCounter() {
        return requestCounter;
    }

    public void setRequestCounter(int requestCounter) {
        this.requestCounter = requestCounter;
    }

    
}
