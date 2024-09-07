package com.simulator.entities;

import jakarta.persistence.Column;

public class CasesDef {




    @Column(name = "CASE_DESC",length = 40)
    private String caseDesc;


    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;


    @Column(name = "CASE_PROTOCOLE",length = 1)
    private Character caseProtocole;


    @Column(name = "CASE_NO",length = 50)
    private String caseNO;

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public Character getCaseProtocole() {
        return caseProtocole;
    }

    public void setCaseProtocole(Character caseProtocole) {
        this.caseProtocole = caseProtocole;
    }

    public String getCaseNO() {
        return caseNO;
    }

    public void setCaseNO(String caseNO) {
        this.caseNO = caseNO;
    }

    public CasesDef (){

    }

    public CasesDef(String caseDesc, String bankCode, Character caseProtocole, String caseNO) {
        this.caseDesc = caseDesc;
        this.bankCode = bankCode;
        this.caseProtocole = caseProtocole;
        this.caseNO = caseNO;
    }


    @Override
    public String toString() {
        return "CasesDef{" +
                "caseDesc='" + caseDesc + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", caseProtocole=" + caseProtocole +
                ", caseNO='" + caseNO + '\'' +
                '}';
    }
}
