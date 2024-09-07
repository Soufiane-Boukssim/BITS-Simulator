package com.simulator.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "fields_definition")
public class FieldsDefinition {
    @EmbeddedId
    private FieldsDefinitionId id;

    @Lob
    @Column(name = "FIELD_DESC")
    private String fieldDesc;

    @Column(name = "FIELD_NAME",length = 100)
    private String fieldName;

    @Column(name = "FIELD_TYPE",length = 10)
    private String fieldType;

    @Column(name = "FIELD_LENGTH_MAX")
    private int fieldLengthMax;

    @Column(name = "FIELD_FORMAT_CHECK",length = 3)
    private String fieldFormatCheck;

    @Column(name = "FIELD_FORMAT_STR",length = 100)
    private String fieldFormatStr;
}
