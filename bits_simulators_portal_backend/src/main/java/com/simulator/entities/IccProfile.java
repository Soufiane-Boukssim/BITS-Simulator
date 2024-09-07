package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

// import jakarta.xml.soap.Name;

@Entity
@Data
@Table(name = "icc_profile")
public class IccProfile {
    @EmbeddedId
    private IccProfileId id;

    @Column(name="IC_PRF_DESC",length = 50)
    private String icPrfDesc;

    @Column(name="IC_TAG_DESC")
    private String icTagDesc;

    @Lob
    @Column(name="IC_TAG_VALUE",length = 500)
    private String icTagValue;

    public IccProfileId getId() {
        return id;
    }

    public void setId(IccProfileId id) {
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

    public IccProfile(IccProfileId id, String icPrfDesc, String icTagDesc, String icTagValue) {
        this.id = id;
        this.icPrfDesc = icPrfDesc;
        this.icTagDesc = icTagDesc;
        this.icTagValue = icTagValue;
    }

    public IccProfile() {
    }

    @Override
    public String toString() {
        return "IccProfile{" +
                "id=" + id +
                ", icPrfDesc='" + icPrfDesc + '\'' +
                ", icTagDesc='" + icTagDesc + '\'' +
                ", icTagValue='" + icTagValue + '\'' +
                '}';
    }
}
