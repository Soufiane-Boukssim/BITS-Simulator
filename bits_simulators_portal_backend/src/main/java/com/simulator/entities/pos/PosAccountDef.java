package com.simulator.entities.pos;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pos_account_def")
public class PosAccountDef {

    @EmbeddedId
    private PosAccountDefId id;
    @Column(name = "ACC_DESC", length = 50)
    private String accDesc;


    public String getAccDesc() {
        return accDesc;
    }

    public void setAccDesc(String accDesc) {
        this.accDesc = accDesc;
    }

    public PosAccountDefId getId() {
        return id;
    }

    public void setId(PosAccountDefId id) {
        this.id = id;
    }

    public PosAccountDef() {
    }

    public PosAccountDef(PosAccountDefId id, String accDesc) {
        this.id = id;
        this.accDesc = accDesc;
    }


}