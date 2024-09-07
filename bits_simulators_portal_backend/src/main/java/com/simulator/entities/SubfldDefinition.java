package com.simulator.entities;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "subfld_definition")
public class SubfldDefinition {
    @EmbeddedId
    private SubfldDefinitionId subfldDefinitionId;
    @Column(name = "SUBFLD_DESCR")
    private String subfldDescr;
    @Column(name = "SUBFLD_TYPE",length = 4)
    private  String subfldType;
    @Column(name = "SUBFLD_LENGTH_MAX")
    private int subfldLengthMax;

    public SubfldDefinitionId getSubfldDefinitionId() {
        return subfldDefinitionId;
    }

    public void setSubfldDefinitionId(SubfldDefinitionId subfldDefinitionId) {
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

    public SubfldDefinition(SubfldDefinitionId subfldDefinitionId, String subfldDescr, String subfldType, int subfldLengthMax) {
        this.subfldDefinitionId = subfldDefinitionId;
        this.subfldDescr = subfldDescr;
        this.subfldType = subfldType;
        this.subfldLengthMax = subfldLengthMax;
    }

    public SubfldDefinition() {
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
