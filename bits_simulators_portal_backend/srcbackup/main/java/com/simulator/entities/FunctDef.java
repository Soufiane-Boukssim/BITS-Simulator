package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "funct_def")
@Data
public class FunctDef {

    @EmbeddedId
    private FunctDefId id;
    @Column(name = "FCT_DESC",length = 50)
    private String fctDesc;

    public FunctDefId getId() {
        return id;
    }

    public void setId(FunctDefId id) { this.id = id; }

    public String getFctDesc() {
        return fctDesc;
    }

    public void setFctDesc(String fctDesc) {
        this.fctDesc = fctDesc;
    }

    public FunctDef() {
    }

    public FunctDef(FunctDefId id, String fctDesc) {
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
