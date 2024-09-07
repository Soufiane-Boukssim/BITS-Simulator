package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "response_def")
@Data
public class ResponseDef {

    @EmbeddedId
    private ResponseDefId id;

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

    public ResponseDefId getId() { return id; }

    public void setId(ResponseDefId id) { this.id = id; }

    public String getRespAction() {
        return respAction;
    }

    public void setRespAction(String respAction) {
        this.respAction = respAction;
    }

    public ResponseDef(ResponseDefId id, String respDesc, String respAction) {
        this.id = id;
        this.respDesc = respDesc;
        this.respAction = respAction;
    }

    public ResponseDef() {
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
