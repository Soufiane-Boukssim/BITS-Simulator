package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class pkChargeCurrCases implements Serializable {
    @GeneratedValue
    private int id;
    private String caseType;
    @Column( length = 1)
    private String protocol;
    private String wording;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
}
