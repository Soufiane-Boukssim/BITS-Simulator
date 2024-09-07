package com.simulator.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "Operation_ATM_Script")
public class OperationAtm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    private String libelle;
    @ManyToOne
    @JsonIgnore
    private AtmCardProfil cardProfile;
    @ManyToMany
    @JoinTable(
            name = "senario_script_definition"
    )
//    @JsonIgnore
    private List<ScriptCasesDefinition> scriptCasesDefinitions;
}
