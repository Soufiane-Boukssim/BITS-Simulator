package com.simulator.entities.pos;

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

public class PosCaseSetInfo {
    @EmbeddedId
    private PosCasesSetInfoId id;

    @Column(name = "CASE_SET_DESC",length = 50)
    private String caseSetDesc;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "pos_case_set_definition"
    )
    private List<PosCasesDefinition> posCasesDefinitions = new ArrayList<>();
}
