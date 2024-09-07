package com.simulator.entities.pos;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosFieldsDefinitionId implements Serializable {
    @Serial
    private static final long serialVersionUID = 8904490429169987558L;

    @Column(name = "FIELD_ID",length = 10)
    private int fieldId;

    @Column(name = "FIELD_PROTOCOLE",length = 2)
    private String fieldProtocole;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public PosFieldsDefinitionId(int fieldId, String fieldProtocole) {
        this.fieldId = fieldId;
        this.fieldProtocole = fieldProtocole;
    }

    @Override
    public String toString() {
        return "FieldsDefinitionId{" +
                "fieldId=" + fieldId +
                ", fieldProtocole=" + fieldProtocole +
                '}';
    }
}
