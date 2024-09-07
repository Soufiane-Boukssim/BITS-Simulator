package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Data
public class FunctDefId implements Serializable {

    @Column(name = "FCT_CODE", nullable = false,length = 3)
    private String fctCode;

    @Column(name = "FCT_PROTO",length = 1, nullable = false)
    private Character fctProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    public String getFctCode() {
        return fctCode;
    }

    public void setFctCode(String fctCode) {
        this.fctCode = fctCode;
    }


    public Character getFctProtocol() {
        return fctProtocol;
    }

    public FunctDefId(String fctCode, Character fctProtocol) {
        this.fctCode = fctCode;
        this.fctProtocol = fctProtocol;
    }
    public void setFctProtocol(Character fctProtocol) {
        this.fctProtocol = fctProtocol;
    }








    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FunctDefId that = (FunctDefId) o;
        return Objects.equals(fctCode, that.fctCode) && Objects.equals(fctProtocol, that.fctProtocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fctCode, fctProtocol);
    }

    public Character getFtcProtocol() {
        return null;
    }
}
