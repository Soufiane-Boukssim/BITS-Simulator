package com.simulator.calculatorsCrypto.model;

public class ResultData {
    private int id;
    private String value;

    // Constructeur par défaut
    public ResultData() {
    }

    // Constructeur avec tous les champs
    public ResultData(int id, String value) {
        this.id = id;
        this.value = value;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    // Méthode toString pour l'affichage
    @Override
    public String toString() {
        return "ResultData{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
