package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "case_set_info")
public class CaseSetInfo {
    @EmbeddedId
    private CasesSetInfoId id;

    @Column(name = "CASE_SET_DESC",length = 50)
    private String caseSetDesc;

    public CasesSetInfoId getId() {
        return id;
    }

    public void setId(CasesSetInfoId id) {
        this.id = id;
    }

    public String getCaseSetDesc() {
        return caseSetDesc;
    }

    public void setCaseSetDesc(String caseSetDesc) {
        this.caseSetDesc = caseSetDesc;
    }

    public CaseSetInfo(CasesSetInfoId id, String caseSetDesc) {
        this.id = id;
        this.caseSetDesc = caseSetDesc;
    }
    public CaseSetInfo(String s, String caseSetDesc) {
        this.id=new CasesSetInfoId(s);
        this.caseSetDesc = caseSetDesc;
    }

    public CaseSetInfo() {
    }

    @Override
    public String toString() {
        return "CaseSetInfo{" +
                "id=" + id +
                ", caseSetDesc='" + caseSetDesc + '\'' +
                '}';
    }
}
