package com.simulator.entities.pos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@NoArgsConstructor
@IdClass(PosFieldValueId.class)
public class PosFieldValue {
    //    @Transient
    @ManyToOne
    @JsonIgnore
    @Id
    private PosCasesDefinition caseDef;
    @ManyToOne
    @Id
    private PosFieldsDefinition fieldDef;
    @Column(name = "value", length = 1000)
    @Lob
    private String value;

    public PosFieldValue(PosFieldsDefinition fieldDef, String value) {
        this.fieldDef = fieldDef;
        this.value = value;
    }
}
