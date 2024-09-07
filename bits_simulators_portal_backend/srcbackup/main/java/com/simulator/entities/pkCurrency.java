package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class pkCurrency implements Serializable {
    private Long code;
    private Long pays_code;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

}
