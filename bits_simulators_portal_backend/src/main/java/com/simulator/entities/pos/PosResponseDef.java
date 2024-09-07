package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pos_response_def")
@Data
public class PosResponseDef {

    @EmbeddedId
    private PosResponseDefId id;

    @Column(name = "RESP_DESC",length = 50)
    private String respDesc;

    @Column(name = "RESP_ACTION",length = 2)
    private String respAction;

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public PosResponseDefId getId() { return id; }

    public void setId(PosResponseDefId id) { this.id = id; }

    public String getRespAction() {
        return respAction;
    }

    public void setRespAction(String respAction) {
        this.respAction = respAction;
    }

    public PosResponseDef(PosResponseDefId id, String respDesc, String respAction) {
        this.id = id;
        this.respDesc = respDesc;
        this.respAction = respAction;
    }

    public PosResponseDef() {
    }

    @Override
    public String toString() {
        return "PosResponseDef{" +
                ", id='" + id + '\'' +
                ", respDesc='" + respDesc + '\'' +
                ", respAction=" + respAction + '\'' +
                '}';
    }
}
