package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "mcc_def")
public class MccDef {

    @EmbeddedId
    private  MccDefId id;

    @Column(name = "MCC_DESC",length = 50)
    private String mccDesc;

    public MccDefId getId() { return id; }

    public void setId(MccDefId id) { this.id = id; }

    public String getMccDesc() {
        return mccDesc;
    }

    public void setMccDesc(String mccDesc) {
        this.mccDesc = mccDesc;
    }


    public MccDef() {
    }

    public MccDef(MccDefId id, String mccDesc) {
        this.id = id;
        this.mccDesc = mccDesc;
    }

    @Override
    public String toString() {
        return "MccDef{" +
                "id='" + id + '\'' +
                ", mccDesc='" + mccDesc + '\'' +
                '}';
    }
}
