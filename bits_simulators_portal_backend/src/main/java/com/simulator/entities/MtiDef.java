package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "mti_def")
public class MtiDef {

    @EmbeddedId
    private MtiDefId id;

    @Column(name = "MTI_DESC",length = 50)
    private String mtiDesc;

    @Column(name = "MTI_DIRECTION",length = 3)
    private String mtiDirection;

    public MtiDefId getId() {
        return id;
    }

    public void setId(MtiDefId id) {
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

    public MtiDef() {
    }

    public MtiDef(MtiDefId id, String mtiDesc, String mtiDirection) {
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
