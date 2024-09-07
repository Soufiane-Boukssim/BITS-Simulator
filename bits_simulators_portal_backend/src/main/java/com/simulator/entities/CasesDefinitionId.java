package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasesDefinitionId implements Serializable {
    @Serial
    private static final long serialVersionUID = -5212134064351303852L;
    @Column(name = "CASE_NO",length = 50)
    private String caseNo;

    @Column(name = "CASE_PROTOCOLE",length = 2)
    private String caseProtocole;

    @Column(name = "CASE_TYPE",length = 1)
    private Character caseType;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public void setCaseProtocole(String caseProtocole) {
        this.caseProtocole = caseProtocole;
    }

    public CasesDefinitionId(String caseNo, String caseProtocole) {
        this.caseNo = caseNo;
        this.caseProtocole = caseProtocole;
    }

    public CasesDefinitionId(String caseNo, String caseProtocole, Character caseType) {
        this.caseNo = caseNo;
        this.caseProtocole = caseProtocole;
        this.caseType = caseType;
    }



    @Override
    public int hashCode() {
        return Objects.hash(caseProtocole, caseNo);
    }
}
