package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class PosInstParamId implements Serializable {
    private static final long serialVersionUID = 7893157155348184597L;


    @Column(name = "INST_PROTO", nullable = false, length = 2)
    private String instProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    public String getInstProtocol() {
        return instProtocol;
    }

    public void setInstProtocol(String instProtocol) {
        this.instProtocol = instProtocol;
    }

    public PosInstParamId(String instProtocol, String bankCode) {
        this.instProtocol = instProtocol;
        this.bankCode = bankCode;
    }

    public PosInstParamId(String instProtocol) {

        this.instProtocol = instProtocol;
    }

    public PosInstParamId() {
    }

    @Override
    public String toString() {
        return "InstParamId{" +
                ", instProtocol='" + instProtocol + '\'' +
                '}';
    }


}
