package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "terminal_keys")
@Data
@NamedQueries({
        @NamedQuery(name = "TerminalKeys.existsByTerminalNumber", query = "select (count(t) > 0) from TerminalKeys t where t.terminalNumber = :terminalNumber")
})
public class TerminalKeys {
    @Id
    @Column(name = "terminal_number", nullable = false, length = 15)
    private String terminalNumber;

    @Column(name = "key_A_old", length = 50)
    private String keyAOld;

    @Column(name = "key_A", length = 50)
    private String keyA;

    @Column(name = "key_A_cv", length = 50)
    private String keyACv;

    @Column(name = "key_B_old", length = 50)
    private String keyBOld;

    @Column(name = "key_B", length = 50)
    private String keyB;

    @Column(name = "key_B_cv", length = 50)
    private String keyBCv;

    @Column(name = "key_MAC_old", length = 50)
    private String keyMacOld;

    @Column(name = "key_MAC", length = 50)
    private String keyMac;

    @Column(name = "key_MAC_cv", length = 50)
    private String keyMacCv;

    @Column(name = "key_visa_old", length = 50)
    private String keyVisaOld;

    @Column(name = "key_visa", length = 50)
    private String keyVisa;

    @Column(name = "key_visa_cv", length = 50)
    private String keyVisaCv;

    @Override
    public String toString() {
        return "TerminalKeys{" +
                "terminalNumber='" + terminalNumber + '\'' +
                ", keyAOld='" + keyAOld + '\'' +
                ", keyA='" + keyA + '\'' +
                ", keyACv='" + keyACv + '\'' +
                ", keyBOld='" + keyBOld + '\'' +
                ", keyB='" + keyB + '\'' +
                ", keyBCv='" + keyBCv + '\'' +
                ", keyMacOld='" + keyMacOld + '\'' +
                ", keyMac='" + keyMac + '\'' +
                ", keyMacCv='" + keyMacCv + '\'' +
                ", keyVisaOld='" + keyVisaOld + '\'' +
                ", keyVisa='" + keyVisa + '\'' +
                ", keyVisaCv='" + keyVisaCv + '\'' +
                '}';
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getKeyAOld() {
        return keyAOld;
    }

    public void setKeyAOld(String keyAOld) {
        this.keyAOld = keyAOld;
    }

    public String getKeyA() {
        return keyA;
    }

    public void setKeyA(String keyA) {
        this.keyA = keyA;
    }

    public String getKeyACv() {
        return keyACv;
    }

    public void setKeyACv(String keyACv) {
        this.keyACv = keyACv;
    }

    public String getKeyBOld() {
        return keyBOld;
    }

    public void setKeyBOld(String keyBOld) {
        this.keyBOld = keyBOld;
    }

    public String getKeyB() {
        return keyB;
    }

    public void setKeyB(String keyB) {
        this.keyB = keyB;
    }

    public String getKeyBCv() {
        return keyBCv;
    }

    public void setKeyBCv(String keyBCv) {
        this.keyBCv = keyBCv;
    }

    public String getKeyMacOld() {
        return keyMacOld;
    }

    public void setKeyMacOld(String keyMacOld) {
        this.keyMacOld = keyMacOld;
    }

    public String getKeyMac() {
        return keyMac;
    }

    public void setKeyMac(String keyMac) {
        this.keyMac = keyMac;
    }

    public String getKeyMacCv() {
        return keyMacCv;
    }

    public void setKeyMacCv(String keyMacCv) {
        this.keyMacCv = keyMacCv;
    }

    public String getKeyVisaOld() {
        return keyVisaOld;
    }

    public void setKeyVisaOld(String keyVisaOld) {
        this.keyVisaOld = keyVisaOld;
    }

    public String getKeyVisa() {
        return keyVisa;
    }

    public void setKeyVisa(String keyVisa) {
        this.keyVisa = keyVisa;
    }

    public String getKeyVisaCv() {
        return keyVisaCv;
    }

    public void setKeyVisaCv(String keyVisaCv) {
        this.keyVisaCv = keyVisaCv;
    }

    public TerminalKeys() {
    }

    public TerminalKeys(String terminalNumber, String keyAOld, String keyA, String keyACv, String keyBOld, String keyB, String keyBCv, String keyMacOld, String keyMac, String keyMacCv, String keyVisaOld, String keyVisa, String keyVisaCv) {
        this.terminalNumber = terminalNumber;
        this.keyAOld = keyAOld;
        this.keyA = keyA;
        this.keyACv = keyACv;
        this.keyBOld = keyBOld;
        this.keyB = keyB;
        this.keyBCv = keyBCv;
        this.keyMacOld = keyMacOld;
        this.keyMac = keyMac;
        this.keyMacCv = keyMacCv;
        this.keyVisaOld = keyVisaOld;
        this.keyVisa = keyVisa;
        this.keyVisaCv = keyVisaCv;
    }
}
