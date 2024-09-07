package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
public class TerminalParamId implements Serializable {

    @Column(name = "TERM_CODE", nullable = false,length = 15)
    private String termCode;

    @Column(name = "TER_PROTO",length = 1)
    private Character terProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public Character getTerProtocol() {
        return terProtocol;
    }

    public void setTerProtocol(Character terProtocol) {
        this.terProtocol = terProtocol;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
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
