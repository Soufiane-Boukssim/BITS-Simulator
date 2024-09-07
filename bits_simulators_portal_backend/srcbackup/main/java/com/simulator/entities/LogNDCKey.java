package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "terminal_ndc_key")
public class LogNDCKey {
    @Id
    @Column(name = "key_type", nullable = false, length = 1)
    private String keyType;

    @Column(name="key_data", length = 255)
    private String keyData;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public LogNDCKey(){}

    public LogNDCKey(String keyType, String keyData) {
        this.keyType = keyType;
        this.keyData = keyData;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyData() {
        return keyData;
    }

    public void setKeyData(String keyData) {
        this.keyData = keyData;
    }

    @Override
    public String toString() {
        return "TerminalNDCKey{" +
                "keyType='" + keyType + '\'' +
                ", keyData='" + keyData + '\'' +
                '}';
    }
}
