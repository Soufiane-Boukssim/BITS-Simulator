package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class InstParamId implements Serializable {
    private static final long serialVersionUID = 7893157155348184597L;
    @Column(name = "SYSTEM_MODE", nullable = false, length = 3)
    private String systemMode;

    @Column(name = "INST_CODE", nullable = false, length = 6)
    private String instCode;

    @Column(name = "INST_PROTO", nullable = false, length = 1)
    private Character instProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    public Character getInstProtocol() {
        return instProtocol;
    }

    public void setInstProtocol(Character instProtocol) {
        this.instProtocol = instProtocol;
    }


    public String getSystemMode() {
        return systemMode;
    }

    public void setSystemMode(String systemMode) {
        this.systemMode = systemMode;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public InstParamId(String systemMode, String instCode, Character instProtocol) {
        this.systemMode = systemMode;
        this.instCode = instCode;
        this.instProtocol = instProtocol;
    }

    public InstParamId() {
    }

    @Override
    public String toString() {
        return "InstParamId{" +
                "systemMode='" + systemMode + '\'' +
                ", instCode='" + instCode + '\'' +
                ", instProtocol='" + instProtocol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstParamId that = (InstParamId) o;
        return Objects.equals(systemMode, that.systemMode) && Objects.equals(instCode, that.instCode) && Objects.equals(instProtocol, that.instProtocol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(systemMode, instCode, instProtocol);
    }
}
