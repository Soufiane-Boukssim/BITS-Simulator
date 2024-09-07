package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class PosMccDefId implements Serializable {

    @Column(name = "MCC_CODE", nullable = false,length = 4)
    private String mccCode;

    @Column(name = "MCC_PROTO",length = 2 ,nullable = false)
    private String mccProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public String getMccCode() { return mccCode; }

    public void setMccCode(String mccCode) { this.mccCode = mccCode; }

    public String getMccProtocol() { return mccProtocol; }

    public void setMccProtocol(String mccProtocol) { this.mccProtocol = mccProtocol; }

    public PosMccDefId(String mccCode, String mccProtocol) {
        this.mccCode = mccCode;
        this.mccProtocol = mccProtocol;
    }

    @Override
    public int hashCode() { return super.hashCode(); }

    @Override
    public boolean equals(Object obj) { return super.equals(obj); }
}
