package com.distribuidos.models;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CompleteTaskRequestParams {
    String serviceName;
    String methodName;
    String arguments;
    int id;

    public CompleteTaskRequestParams(){};

    public CompleteTaskRequestParams(String serviceName, String methodName, String arguments, int id) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.arguments = arguments;
        this.id = id;
    }

    public static String empacotar(CompleteTaskRequestParams completeTaskRequestParams) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(completeTaskRequestParams);   
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return json;
    }

    public static CompleteTaskRequestParams desempacotar(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        CompleteTaskRequestParams completeTaskRequestParams = null;
        try {
            completeTaskRequestParams = objectMapper.readValue(json, CompleteTaskRequestParams.class);
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return completeTaskRequestParams;
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
