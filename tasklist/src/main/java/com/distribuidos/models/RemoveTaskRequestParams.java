package com.distribuidos.models;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RemoveTaskRequestParams {
    String serviceName;
    String methodName;
    String arguments;
    int id;

    public RemoveTaskRequestParams(){};

    public RemoveTaskRequestParams(String serviceName, String methodName, String arguments, int id) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.arguments = arguments;
        this.id = id;
    }

    public static String empacotar(RemoveTaskRequestParams removeTaskRequestParams) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(removeTaskRequestParams);   
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return json;
    }

    public static RemoveTaskRequestParams desempacotar(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        RemoveTaskRequestParams removeTaskRequestParams = null;
        try {
            removeTaskRequestParams = objectMapper.readValue(json, RemoveTaskRequestParams.class);
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return removeTaskRequestParams;
    }

    public String getArguments() {
        return arguments;
    }
    public String getMethodName() {
        return methodName;
    }
    public String getServiceName() {
        return serviceName;
    }
    public int getId() {
        return id;
    }
}
