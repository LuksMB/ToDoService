package com.distribuidos.models;

public class RemoveTaskRequest {
    String idRequest;

    public RemoveTaskRequest(String idRequest) {
        this.idRequest = idRequest;
    } 

    public RemoveTaskRequest() {
    }

    public String getIdRequest() {
        return idRequest;
    }

    public void setIdRequest(String idRequest) {
        this.idRequest = idRequest;
    }
}