package com.simulator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "case_set_info")
public class CaseSetInfo {
    @EmbeddedId
    private CasesSetInfoId id;

    @Column(name = "CASE_SET_DESC",length = 50)
    private String caseSetDesc;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "case_set_definition"
    )
    private List<CasesDefinition> casesDefinitions = new ArrayList<>();



//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("CaseSetInfo(id=").append(id);
//        sb.append(", caseSetDesc=").append(caseSetDesc);
//        sb.append(", casesDefinitions=[");
//        for (CasesDefinition casesDefinition : casesDefinitions) {
//            sb.append(casesDefinition.toString()).append(", ");
//        }
//        sb.append("])");
//        return sb.toString();
//    }

}
