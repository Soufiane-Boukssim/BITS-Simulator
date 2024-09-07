package com.simulator.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simulator.atm.model.enm.TypeDefinitionScript;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Definition_ATM_Script")
public class ScriptCasesDefinition {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCasesDefinition;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    private String libelle;
    @Enumerated(EnumType.STRING)
    private TypeDefinitionScript typeScript;
    @ManyToOne
    @JsonIgnore
    private AtmCardProfil cardProfile;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<FieldCases> fieldCases;
}
