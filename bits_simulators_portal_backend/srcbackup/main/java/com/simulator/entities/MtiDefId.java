package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
@AllArgsConstructor
public class MtiDefId implements Serializable {

    @Column(name = "MTI_CODE", nullable = false,length = 4)
    private String mtiCode;
    @Column(name = "MTI_PROTOCOL",length = 1)
    private String mtiProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public String getMtiCode() {
        return mtiCode;
    }

    public void setMtiCode(String mtiCode) {
        this.mtiCode = mtiCode;
    }

    public String getMtiProtocol() {
        return mtiProtocol;
    }

    public void setMtiProtocol(String mtiProtocol) {
        this.mtiProtocol = mtiProtocol;
    }

}
