package com.distribuidos.models;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddTaskRequestParams {
    String serviceName;
    String methodName;
    String arguments;
    int id;

    public AddTaskRequestParams(){};

    public AddTaskRequestParams(String serviceName, String methodName, String arguments, int id) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.arguments = arguments;
        this.id = id;
    }

    public static String empacotar(AddTaskRequestParams addTaskRequestParams) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(addTaskRequestParams);   
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return json;
    }

    public static AddTaskRequestParams desempacotar(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        AddTaskRequestParams addTaskRequestParams = null;
        try {
            addTaskRequestParams = objectMapper.readValue(json, AddTaskRequestParams.class);
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return addTaskRequestParams;
    }

    public String getArguments() {
        return arguments;
    }

    public String getServiceName() {
        return serviceName;
    }
    public String getMethodName() {
        return methodName;
    }
    public int getId() {
        return id;
    }
}
