package com.simulator.calculatorsCrypto.controller;

public class DesMBDTO {
    private String cipheredData;
    private String decipheredData;

    public DesMBDTO(String cipheredData, String decipheredData) {
        this.cipheredData = cipheredData;
        this.decipheredData = decipheredData;
    }

    public String getCipheredData() {
        return cipheredData;
    }

    public void setCipheredData(String cipheredData) {
        this.cipheredData = cipheredData;
    }

    public String getDecipheredData() {
        return decipheredData;
    }

    public void setDecipheredData(String decipheredData) {
        this.decipheredData = decipheredData;
    }
}
