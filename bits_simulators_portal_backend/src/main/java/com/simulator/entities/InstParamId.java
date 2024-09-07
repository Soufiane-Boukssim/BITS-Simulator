package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class InstParamId implements Serializable {
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

    public InstParamId(String instProtocol, String bankCode) {
        this.instProtocol = instProtocol;
        this.bankCode = bankCode;
    }

    public InstParamId(String instProtocol) {

        this.instProtocol = instProtocol;
    }

    public InstParamId() {
    }

    @Override
    public String toString() {
        return "InstParamId{" +
                ", instProtocol='" + instProtocol + '\'' +
                '}';
    }


}
