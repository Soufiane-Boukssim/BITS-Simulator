package com.simulator.atm.model;

import com.simulator.atm.model.enm.PopularList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Field_Cases")
public class FieldCases {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idFieldCases;
    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;
    @Enumerated(EnumType.STRING)
    private PopularList popularList;
    private String value;
    @ManyToMany
    private List<ATMfield> atmField;
    @ManyToOne
    private ScriptCasesDefinition scriptCasesDefinition;

}
