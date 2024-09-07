package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pos_mti_def")
public class PosMtiDef {

    @EmbeddedId
    private PosMtiDefId id;

    @Column(name = "MTI_DESC",length = 50)
    private String mtiDesc;

    @Column(name = "MTI_DIRECTION",length = 3)
    private String mtiDirection;

    public PosMtiDefId getId() {
        return id;
    }

    public void setId(PosMtiDefId id) {
        this.id = id;
    }

    public String getMtiDesc() {
        return mtiDesc;
    }

    public void setMtiDesc(String mtiDesc) {
        this.mtiDesc = mtiDesc;
    }

    public String getMtiDirection() {
        return mtiDirection;
    }

    public void setMtiDirection(String mtiDirection) {
        this.mtiDirection = mtiDirection;
    }

    public PosMtiDef() {
    }

    public PosMtiDef(PosMtiDefId id, String mtiDesc, String mtiDirection) {
        this.id = id;
        this.mtiDesc = mtiDesc;
        this.mtiDirection = mtiDirection;
    }

    @Override
    public String toString() {
        return "MtiDef{" +
                "id='" + id.getMtiCode() + " - " + id.getBankCode() + " - " + id.getMtiProtocol() + '\'' +
                ", mtiDesc='" + mtiDesc + '\'' +
                ", mtiDirection=" + mtiDirection +
                '}';
    }
}
