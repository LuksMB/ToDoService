package com.distribuidos.models;

public class CompleteTaskRequest {
    String idRequest;
    
    public CompleteTaskRequest(String idRequest) {
        this.idRequest = idRequest;
    }
    public CompleteTaskRequest() {}


    public String getIdRequest() {
        return this.idRequest;
    }

    public void setIdRequest(String idRequest) {
        this.idRequest = idRequest;
    }
}
