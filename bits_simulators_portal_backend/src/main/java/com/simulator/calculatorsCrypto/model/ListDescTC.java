package com.simulator.calculatorsCrypto.model;



public class ListDescTC {


    String id;
    String description;


    public ListDescTC(String id, String description){
        this.id = id;
        this.description = description;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

}
