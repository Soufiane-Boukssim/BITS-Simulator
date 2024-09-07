package com.simulator.entities.pos;

import jakarta.persistence.*;
import lombok.Data;

// import jakarta.xml.soap.Name;

@Entity
@Data
public class PosIccProfile {
    @EmbeddedId
    private PosIccProfileId id;

    @Column(name="IC_PRF_DESC",length = 50)
    private String icPrfDesc;

    @Column(name="IC_TAG_DESC")
    private String icTagDesc;

    @Lob
    @Column(name="IC_TAG_VALUE",length = 500)
    private String icTagValue;

    public PosIccProfileId getId() {
        return id;
    }

    public void setId(PosIccProfileId id) {
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

    public PosIccProfile(PosIccProfileId id, String icPrfDesc, String icTagDesc, String icTagValue) {
        this.id = id;
        this.icPrfDesc = icPrfDesc;
        this.icTagDesc = icTagDesc;
        this.icTagValue = icTagValue;
    }

    public PosIccProfile() {
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
