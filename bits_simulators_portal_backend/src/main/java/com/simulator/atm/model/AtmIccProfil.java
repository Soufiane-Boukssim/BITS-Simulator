package com.simulator.atm.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class AtmIccProfil {

    @EmbeddedId
    private AtmIccProfilId id;

    @Column(name="IC_PRF_DESC",length = 50)
    private String icPrfDesc;

    @Column(name="IC_TAG_DESC")
    private String icTagDesc;

    @Lob
    @Column(name="IC_TAG_VALUE",length = 500)
    private String icTagValue;

    public AtmIccProfilId getId() {
        return id;
    }

    public void setId(AtmIccProfilId id) {
        this.id = id;
    }

    public String getIcPrfDesc() {
        return icPrfDesc;
    }

    public void setIcPrfDesc(String icPrfDesc) {
        this.icPrfDesc = icPrfDesc;
    }

    public String getIcTagDesc() {
        return icTagDesc;
    }

    public void setIcTagDesc(String icTagDesc) {
        this.icTagDesc = icTagDesc;
    }

    public String getIcTagValue() {
        return icTagValue;
    }

    public void setIcTagValue(String icTagValue) {
        this.icTagValue = icTagValue;
    }

    public AtmIccProfil(AtmIccProfilId id, String icPrfDesc, String icTagDesc, String icTagValue) {
        this.id = id;
        this.icPrfDesc = icPrfDesc;
        this.icTagDesc = icTagDesc;
        this.icTagValue = icTagValue;
    }

    public AtmIccProfil() {
    }

    @Override
    public String toString() {
        return "PosIccProfile{" +
                "id=" + id +
                ", icPrfDesc='" + icPrfDesc + '\'' +
                ", icTagDesc='" + icTagDesc + '\'' +
                ", icTagValue='" + icTagValue + '\'' +
                '}';
    }
}
