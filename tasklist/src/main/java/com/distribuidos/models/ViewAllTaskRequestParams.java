package com.distribuidos.models;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ViewAllTaskRequestParams {
    String serviceName;
    String methodName;
    String arguments;
    int id;

    public ViewAllTaskRequestParams(){};

    public ViewAllTaskRequestParams(String serviceName, String methodName, String arguments, int id) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.arguments = arguments;
        this.id = id;
    }

    public static String empacotar(ViewAllTaskRequestParams viewAllTaskRequestParams) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(viewAllTaskRequestParams);   
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return json;
    }

    public static ViewAllTaskRequestParams desempacotar(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        ViewAllTaskRequestParams viewAllTaskRequestParams = null;
        try {
            viewAllTaskRequestParams = objectMapper.readValue(json, ViewAllTaskRequestParams.class);
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return viewAllTaskRequestParams;
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
