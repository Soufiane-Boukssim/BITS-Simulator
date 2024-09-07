package com.simulator.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
public class ResponseDefId implements Serializable {


    @Column(name = "RESP_CODE", nullable = false,length = 3)
    private String respCode;


    @Column(name = "RESP_PROTO",length = 1, nullable = false)
    private Character respProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public Character getRespProtocol() {
        return respProtocol;
    }

    public void setRespProtocol(Character respProtocol) {
        this.respProtocol = respProtocol;
    }

    public ResponseDefId(String respCode,Character respProtocol) {
        this.respCode = respCode;
        this.respProtocol = respProtocol;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
