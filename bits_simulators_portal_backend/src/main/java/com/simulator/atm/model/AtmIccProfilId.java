package com.simulator.atm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;


@Embeddable
@Data
public class AtmIccProfilId implements Serializable {
    private static final long serialVersionUID = -4893412773354849100L;

    @Column(name="IC_PRF")
    private String icPrf;
    @Column(name="IC_TAG",length = 50)
    private String icTag;


    @Column(name = "ICC_PROTOCOL",length = 1)
    private String iccProtocol;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public AtmIccProfilId(String icPrf, String icTag) {
        this.icPrf = icPrf;
        this.icTag = icTag;
    }

    public AtmIccProfilId() {
    }

    public String getIccProtocol() {
        return iccProtocol;
    }

    public void setIccProtocol(String iccProtocol) {
        this.iccProtocol = iccProtocol;
    }


    public String getIcPrf() {
        return icPrf;
    }

    public void setIcPrf(String icPrf) {
        this.icPrf = icPrf;
    }

    public String getIcTag() {
        return icTag;
    }

    public void setIcTag(String icTag) {
        this.icTag = icTag;
    }

    @Override
    public String toString() {
        return "PosIccProfileId{" +
                "icPrf='" + icPrf + '\'' +
                "iccProtocol='" + iccProtocol + '\'' +
                ", icTag='" + icTag + '\'' +
                '}';
    }
}
