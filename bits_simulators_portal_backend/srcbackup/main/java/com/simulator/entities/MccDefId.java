package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class MccDefId implements Serializable {

    @Column(name = "MCC_CODE", nullable = false,length = 4)
    private String mccCode;

    @Column(name = "MCC_PROTO",length = 1 ,nullable = false)
    private Character mccProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public String getMccCode() { return mccCode; }

    public void setMccCode(String mccCode) { this.mccCode = mccCode; }

    public Character getMccProtocol() { return mccProtocol; }

    public void setMccProtocol(Character mccProtocol) { this.mccProtocol = mccProtocol; }

    public MccDefId(String mccCode, Character mccProtocol) {
        this.mccCode = mccCode;
        this.mccProtocol = mccProtocol;
    }

    @Override
    public int hashCode() { return super.hashCode(); }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }
}
