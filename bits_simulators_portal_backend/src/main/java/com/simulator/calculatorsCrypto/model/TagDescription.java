package com.simulator.calculatorsCrypto.model;

public class TagDescription {

    String tag;
    String name;
    String description;

    public TagDescription() {
        // TODO Auto-generated constructor stub
    }

    public TagDescription(String tag, String name, String description) {
        super();
        this.tag = tag;
        this.name = name;
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TagDescription [tag=" + tag + ", name=" + name + ", description=" + description + "]";
    }

}
