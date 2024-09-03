package com.distribuidos;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Mensagem {
    private int messageType;
    private int id;
    private String objectReference;
    private String methodId;
    private String arguments;

    public Mensagem() {
        this.messageType = 0;
        this.id = 0;
        this.objectReference = "objectReference";
        this.methodId = "methodId";
        this.arguments = "arguments";
    }

    public Mensagem(int messageType, int id, String objectReference, String methodId, String arguments) {
        this.messageType = messageType;
        this.id = id;
        this.objectReference = objectReference;
        this.methodId = methodId;
        this.arguments = arguments;
    }

    public static String empacotarMensagem(Mensagem msg){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(msg);   
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return json;
    }

    public static Mensagem desempacotarMensagem(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        Mensagem msg = new Mensagem();
        try {
            msg = objectMapper.readValue(json, Mensagem.class);
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return msg;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjectReference() {
        return objectReference;
    }

    public void setObjectReference(String objectReference) {
        this.objectReference = objectReference;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }
}
