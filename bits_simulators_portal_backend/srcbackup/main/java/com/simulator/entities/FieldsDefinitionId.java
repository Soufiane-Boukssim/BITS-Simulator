package com.simulator.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class FieldsDefinitionId implements Serializable {
    private static final long serialVersionUID = 8904490429169987558L;

    @Column(name = "FIELD_ID",length = 10)
    private int fieldId;

    @Column(name = "FIELD_PROTOCOLE",length = 1)
    private Character fieldProtocole;

    @Column(name = "BANK_CODE", length = 5)
    private String bankCode;

    public int getFieldId() {
        return fieldId;
    }

    public void setFieldId(int fieldId) {
        this.fieldId = fieldId;
    }

    public Character getFieldProtocole() {
        return fieldProtocole;
    }

    public void setFieldProtocole(Character fieldProtocole) {
        this.fieldProtocole = fieldProtocole;
    }

    public FieldsDefinitionId() {
    }

    public FieldsDefinitionId(int fieldId, Character fieldProtocole) {
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
