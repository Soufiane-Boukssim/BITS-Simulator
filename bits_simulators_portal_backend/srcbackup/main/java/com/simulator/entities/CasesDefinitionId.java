package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class CasesDefinitionId implements Serializable {
    private static final long serialVersionUID = -5212134064351303852L;
    @Column(name = "CASE_NO",length = 50)
    private String caseNo;

    @Column(name = "CASE_PROTOCOLE",length = 1)
    private Character caseProtocole;

    @Column(name = "CASE_TYPE",length = 1)
    private Character caseType;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public Character getCaseType() {
        return caseType;
    }

    public void setCaseType(Character caseType) {
        this.caseType = caseType;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public Character getCaseProtocole() {
        return caseProtocole;
    }

    public void setCaseProtocole(Character caseProtocole) {
        this.caseProtocole = caseProtocole;
    }

    public CasesDefinitionId(String caseNo, Character caseProtocole) {
        this.caseNo = caseNo;
        this.caseProtocole = caseProtocole;
    }

    public CasesDefinitionId(String caseNo, Character caseProtocole, Character caseType) {
        this.caseNo = caseNo;
        this.caseProtocole = caseProtocole;
        this.caseType = caseType;
    }

    public CasesDefinitionId() {
    }

    @Override
    public String toString() {
        return "CasesDefinitionId{" +
                "caseNo='" + caseNo + '\'' +
                ", caseProtocole=" + caseProtocole +
                ", caseType=" + caseType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CasesDefinitionId entity = (CasesDefinitionId) o;
        return Objects.equals(this.caseProtocole, entity.caseProtocole) &&
                Objects.equals(this.caseNo, entity.caseNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(caseProtocole, caseNo);
    }
}
