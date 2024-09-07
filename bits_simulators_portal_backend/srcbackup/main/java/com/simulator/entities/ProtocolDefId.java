package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@NoArgsConstructor
public class ProtocolDefId implements Serializable {

    @Column(name = "PROT_CODE", nullable = false,length = 3)
    private String protCode;

    @Column(name = "prot_proto",length = 1, nullable = false)
    private Character protProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public String getProtCode() {
        return protCode;
    }

    public void setProtCode(String protCode) {
        this.protCode = protCode;
    }

    public Character getProtProtocol() {
        return protProtocol;
    }

    public ProtocolDefId(String protCode, Character protProtocol) {
        this.protCode = protCode;
        this.protProtocol = protProtocol;
    }

    public void setProtProtocol(Character protProtocol) {
        this.protProtocol = protProtocol;
    }



    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
