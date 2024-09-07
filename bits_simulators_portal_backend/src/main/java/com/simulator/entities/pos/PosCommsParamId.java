package com.simulator.entities.pos;

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
public class PosCommsParamId implements Serializable {

    @Column(name = "COMM_ID", nullable = false)
    private Integer commId;

    @Column(name = "COMM_PROTO", length = 2, nullable = false)
    private String commProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
    }

    public String getCommProtocol() {
        return commProtocol;
    }

    public void setCommProtocol(String commProtocol) {
        this.commProtocol = commProtocol;
    }
}
