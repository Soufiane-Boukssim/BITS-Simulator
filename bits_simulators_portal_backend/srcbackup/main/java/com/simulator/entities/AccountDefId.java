package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDefId implements Serializable {

    @Column(name = "ACC_CODE", nullable = false,length = 3)
    private String accCode;

    @Column(name = "ACC_PROTO",length = 1, nullable = false)
    private Character accProtocol;


    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
