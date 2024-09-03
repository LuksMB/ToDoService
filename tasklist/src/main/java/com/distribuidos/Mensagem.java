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
