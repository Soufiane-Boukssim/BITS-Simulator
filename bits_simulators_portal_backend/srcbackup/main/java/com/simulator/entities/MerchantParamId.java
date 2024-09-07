package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Embeddable
@Data
@NoArgsConstructor
public class MerchantParamId implements Serializable {

    @Column(name = "MER_CODE", nullable = false)
    private String merCode;

    @Column(name = "MER_PROTO")
    private Character merProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public String getMerCode() {
        return merCode;
    }

    public void setMerCode(String merCode) {
        this.merCode = merCode;
    }

    public Character getMerProtocol() {
        return merProtocol;
    }

    public void setMerProtocol(Character merProtocol) {
        this.merProtocol = merProtocol;
    }

    public MerchantParamId(String merCode, Character merProtocol) {
        this.merCode = merCode;
        this.merProtocol = merProtocol;
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
