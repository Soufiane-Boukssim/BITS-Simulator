package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pos_mcc_def")
public class PosMccDef {

    @EmbeddedId
    private PosMccDefId id;

    @Column(name = "MCC_DESC",length = 50)
    private String mccDesc;

    public PosMccDefId getId() { return id; }

    public void setId(PosMccDefId id) { this.id = id; }

    public String getMccDesc() {
        return mccDesc;
    }

    public void setMccDesc(String mccDesc) {
        this.mccDesc = mccDesc;
    }


    public PosMccDef() {
    }

    public PosMccDef(PosMccDefId id, String mccDesc) {
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
