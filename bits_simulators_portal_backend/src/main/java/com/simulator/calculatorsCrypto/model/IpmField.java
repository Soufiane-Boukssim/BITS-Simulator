package com.simulator.calculatorsCrypto.model;

public class IpmField {

    private String id;
    private String description;
    private int position;
    private int length;
    private String value;

    public IpmField(String id, String description, int position, int length, String value) {
        this.id = id;
        this.description = description;
        this.position = position;
        this.length = length;
        this.value = value;
    }

    public IpmField(int id, String description, int position, int length, String value) {
        this.id = String.format("%03d",id);
        this.description = description;
        this.position = position;
        this.length = length;
        this.value = value;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
