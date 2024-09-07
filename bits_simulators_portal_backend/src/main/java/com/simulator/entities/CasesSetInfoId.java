package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasesSetInfoId implements Serializable {
    @Serial
    private static final long serialVersionUID = -6693943421119343600L;


    @Column(name = "CASE_SET_ID", length = 50)
    private String caseSetId;

    @Column(name = "CASE_SET_PROTOCOLE", length = 2)
    private String caseSetProtocole;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
}
