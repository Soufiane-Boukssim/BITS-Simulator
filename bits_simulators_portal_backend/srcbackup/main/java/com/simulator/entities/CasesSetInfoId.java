package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class CasesSetInfoId implements Serializable {

    private static final long serialVersionUID = -6693943421119343600L;
    @Column(name = "CASE_SET_ID",length = 50)
    private String caseSetId;

    @Column(name = "CASE_NO",length = 50)
    private String caseNo;

    @Column(name = "CASE_SET_PROTOCOLE",length = 1)
    private Character caseSetProtocole;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public String getCaseSetId() {
        return caseSetId;
    }

    public void setCaseSetId(String caseSetId) {
        this.caseSetId = caseSetId;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Character getCaseSetProtocole() {
        return caseSetProtocole;
    }

    public void setCaseSetProtocole(Character caseSetProtocole) {
        this.caseSetProtocole = caseSetProtocole;
    }

    public CasesSetInfoId(String caseSetId, String caseNo, Character caseSetProtocole) {
        this.caseSetId = caseSetId;
        this.caseNo = caseNo;
        this.caseSetProtocole = caseSetProtocole;
    }

    public CasesSetInfoId(String caseSetId){
        this.caseSetId=caseSetId;
    }

    public CasesSetInfoId() {
    }

    @Override
    public String toString() {
        return "CasesSetInfo{" +
                "caseSetId='" + caseSetId + '\'' +
                ", caseNo='" + caseNo + '\'' +
                ", caseSetProtocole=" + caseSetProtocole +
                '}';
    }
}
