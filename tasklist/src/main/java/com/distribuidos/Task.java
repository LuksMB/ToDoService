package com.distribuidos;

import java.io.Serializable;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Task implements Serializable{

    private String title;
    private String description;
    private Boolean state;

    

    public Task() {
        this.title = "title";
        this.description = "description";
        this.state = false;
    }

    public Task(String title, String description, Boolean state){
        this.title = title;
        this.description = description;
        this.state = state;
    }

    public String serializar(){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(this);   
        } catch (Exception e) {
            System.out.println("INFO: " + e);
        }
        return json;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
