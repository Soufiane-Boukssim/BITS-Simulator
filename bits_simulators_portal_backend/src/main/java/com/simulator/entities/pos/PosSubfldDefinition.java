package com.simulator.entities.pos;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class PosSubfldDefinition {
    @EmbeddedId
    private PosSubfldDefinitionId subfldDefinitionId;

    @Column(name = "SUBFLD_DESCR")
    private String subfldDescr;

    @Column(name = "SUBFLD_TYPE",length = 4)
    private  String subfldType;

    @Column(name = "SUBFLD_LENGTH_MAX")
    private int subfldLengthMax;

    public PosSubfldDefinitionId getSubfldDefinitionId() {
        return subfldDefinitionId;
    }

    public void setSubfldDefinitionId(PosSubfldDefinitionId subfldDefinitionId) {
        this.subfldDefinitionId = subfldDefinitionId;
    }

    public String getSubfldDescr() {
        return subfldDescr;
    }

    public void setSubfldDescr(String subfldDescr) {
        this.subfldDescr = subfldDescr;
    }

    public String getSubfldType() {
        return subfldType;
    }

    public void setSubfldType(String subfldType) {
        this.subfldType = subfldType;
    }

    public int getSubfldLengthMax() {
        return subfldLengthMax;
    }

    public void setSubfldLengthMax(int subfldLengthMax) {
        this.subfldLengthMax = subfldLengthMax;
    }

    public PosSubfldDefinition(PosSubfldDefinitionId subfldDefinitionId, String subfldDescr, String subfldType, int subfldLengthMax) {
        this.subfldDefinitionId = subfldDefinitionId;
        this.subfldDescr = subfldDescr;
        this.subfldType = subfldType;
        this.subfldLengthMax = subfldLengthMax;
    }

    public PosSubfldDefinition() {
    }

    @Override
    public String toString() {
        return "SubfldDefinition{" +
                "subfldDefinitionId=" + subfldDefinitionId +
                ", subfldDescr='" + subfldDescr + '\'' +
                ", subfldType='" + subfldType + '\'' +
                ", subfldLengthMax=" + subfldLengthMax +
                '}';
    }
}
