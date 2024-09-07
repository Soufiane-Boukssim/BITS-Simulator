package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PosAccountDefId implements Serializable {

    @Column(name = "ACC_CODE", nullable = false,length = 3)
    private String accCode;

    @Column(name = "ACC_PROTO",length = 2, nullable = false)
    private String accProtocol;


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
