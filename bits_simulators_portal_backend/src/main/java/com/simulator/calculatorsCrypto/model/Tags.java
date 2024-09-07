package com.simulator.calculatorsCrypto.model;

public class Tags {
    String aipByte;
    String aipBit;
    String meaning;
    boolean present;

    public Tags(String aipByte, String aipBit, String meaning, boolean present) {
        this.aipByte = aipByte;
        this.aipBit = aipBit;
        this.meaning = meaning;
        this.present = present;
    }

    public String getAipByte() {
        return aipByte;
    }

    public void setAipByte(String aipByte) {
        this.aipByte = aipByte;
    }

    public String getAipBit() {
        return aipBit;
    }

    public void setAipBit(String aipBit) {
        this.aipBit = aipBit;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
