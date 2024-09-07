package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

    @Column(name = "FIELD_FORMAT_INT")
    private int fieldFormatInt;

    @Column(name = "FIELD_LENGTH_MAX")
    private int fieldLengthMax;

    @Column(name = "FIELD_FORMAT_CHECK",length = 3)
    private String fieldFormatCheck;

    @Column(name = "FIELD_FORMAT_STR",length = 100)
    private String fieldFormatStr;

    @Column(name = "FIELD_VALUE",length = 255)
    private String fieldValue;


    public FieldsDefinitionId getFieldsDefinitionId() {
        return id;
    }

    public void setFieldsDefinitionId(FieldsDefinitionId id) {
        this.id = id;
    }

    public String getFieldDesc() {
        return fieldDesc;
    }

    public void setFieldDesc(String fieldDesc) {
        this.fieldDesc = fieldDesc;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public int getFieldFormatInt() {
        return fieldFormatInt;
    }

    public void setFieldFormatInt(int fieldFormatInt) {
        this.fieldFormatInt = fieldFormatInt;
    }

    public int getFieldLengthMax() {
        return fieldLengthMax;
    }

    public void setFieldLengthMax(int fieldLengthMax) {
        this.fieldLengthMax = fieldLengthMax;
    }

    public String getFieldFormatCheck() {
        return fieldFormatCheck;
    }

    public void setFieldFormatCheck(String fieldFormatCheck) {
        this.fieldFormatCheck = fieldFormatCheck;
    }

    public String getFieldFormatStr() {
        return fieldFormatStr;
    }

    public void setFieldFormatStr(String fieldFormatStr) {
        this.fieldFormatStr = fieldFormatStr;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public FieldsDefinition(FieldsDefinitionId id, String fieldDesc, String fieldName, String fieldType, int fieldFormatInt, int fieldLengthMax, String fieldFormatCheck, String fieldFormatStr, String fieldValue) {
        this.id = id;
        this.fieldDesc = fieldDesc;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldFormatInt = fieldFormatInt;
        this.fieldLengthMax = fieldLengthMax;
        this.fieldFormatCheck = fieldFormatCheck;
        this.fieldFormatStr = fieldFormatStr;
        this.fieldValue = fieldValue;
    }

    public FieldsDefinition() {
    }

    @Override
    public String toString() {
        return "FieldsDefinition{" +
                "id=" + id.toString() +
                ", fieldDesc='" + fieldDesc + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", fieldType=" + fieldType +
                ", fieldFormatInt=" + fieldFormatInt +
                ", fieldLengthMax=" + fieldLengthMax +
                ", fieldFormatCheck=" + fieldFormatCheck +
                ", fieldFormatStr='" + fieldFormatStr + '\'' +
                ", fieldValue='" + fieldValue + '\'' +
                '}';
    }
}
