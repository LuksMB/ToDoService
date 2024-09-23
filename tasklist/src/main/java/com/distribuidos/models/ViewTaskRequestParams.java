package com.distribuidos.models;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ViewTaskRequestParams {
    String serviceName;
    String methodName;
    String arguments;
    int id;

    public ViewTaskRequestParams(){};

    public ViewTaskRequestParams(String serviceName, String methodName, String arguments, int id) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.arguments = arguments;
        this.id = id;
    }

    public static String empacotar(ViewTaskRequestParams viewTaskRequestParams) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(viewTaskRequestParams);   
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return json;
    }

    public static ViewTaskRequestParams desempacotar(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        ViewTaskRequestParams viewTaskRequestParams = null;
        try {
            viewTaskRequestParams = objectMapper.readValue(json, ViewTaskRequestParams.class);
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return viewTaskRequestParams;
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
