package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pos_funct_def")
public class PosFunctDef {

    @EmbeddedId
    private PosFunctDefId id;
    @Column(name = "FCT_DESC",length = 50)
    private String fctDesc;

    public PosFunctDefId getId() {
        return id;
    }

    public void setId(PosFunctDefId id) { this.id = id; }

    public String getFctDesc() {
        return fctDesc;
    }

    public void setFctDesc(String fctDesc) {
        this.fctDesc = fctDesc;
    }

    public PosFunctDef() {
    }

    public PosFunctDef(PosFunctDefId id, String fctDesc) {
        this.id = id;
        this.fctDesc = fctDesc;
    }

    @Override
    public String toString() {
        return "Funct_Def{" +
                "id='" + id + '\'' +
                ", fctDesc='" + fctDesc + '\'' +
                '}';
    }
}
