package com.simulator.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "field_value")
@Data
@NoArgsConstructor
@IdClass(FieldValueId.class)
public class FieldValue {
    //    @Transient
    @ManyToOne
    @JsonIgnore
    @Id
    private CasesDefinition caseDef;
    @ManyToOne
    @Id
    private FieldsDefinition fieldDef;
    @Column(name = "value", length = 1000)
    @Lob
    private String value;

    public FieldValue(FieldsDefinition fieldDef, String value) {
        this.fieldDef = fieldDef;
        this.value = value;
    }
}