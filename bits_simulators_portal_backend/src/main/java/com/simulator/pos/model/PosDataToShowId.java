package com.simulator.pos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosDataToShowId {

    @Serial
    private static final long serialVersionUID = -6693943421119343600L;

    @Column(name = "caseNo", length = 50)
    private String caseNo;

    @Column(name = "caseType", length = 50)
    private String caseType;

    @Column(name = "caseProtocole", length = 2)
    private String caseProtocole;

    @Column(name = "bankCode", length = 5)
    private String bankCode;
}
