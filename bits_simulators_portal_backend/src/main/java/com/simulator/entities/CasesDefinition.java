package com.simulator.entities;

import com.simulator.iso.model.IsoFields;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cases_definition")
@Data
@NoArgsConstructor
public class CasesDefinition {
    @EmbeddedId
    private CasesDefinitionId id;

    @Column(name = "CASE_DESC",length = 50)
    private String caseDesc;

    @Column(name = "CASE_DIRECTION",length = 3)
    private String caseDirection;

    @Column(name = "CASE_CARDREF",length = 50)
    private String caseCardRef;

    @Column(name = "CASE_MERCHANTPRF",length = 50)
    private String caseMerchantPrf;

    @Column(name = "CASE_TERMINALPRF",length = 50)
    private String caseTerminalPrf;

    @Column(name = "CASE_AMOUNT")
    private int caseAmount;

    @Column(name = "CASE_HEADER",length = 50)
    private String caseHeader;

    @Column(name = "CASE_MTI",length = 50)
    private String caseMti;

    @Column(name = "CASE_ACTION",length = 2)
    private String caseAction;

    @ToString.Exclude
    @OneToMany(mappedBy = "caseDef", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FieldValue> fields = new ArrayList<>();

    public CasesDefinition(CasesDefinitionId id, String caseDesc, String caseDirection, String caseCardRef, String caseMerchantPrf, String caseTerminalPrf, int caseAmount, String caseHeader, String caseMti, List<FieldValue> fields) {
        this.id = id;
        this.caseDesc = caseDesc;
        this.caseDirection = caseDirection;
        this.caseCardRef = caseCardRef;
        this.caseMerchantPrf = caseMerchantPrf;
        this.caseTerminalPrf = caseTerminalPrf;
        this.caseAmount = caseAmount;
        this.caseHeader = caseHeader;
        this.caseMti = caseMti;
//        for (FieldValue field : fields) {
//            System.out.println("***********" + field.getValue() + "***********");
//            field.setCaseDef(this);
//        }
        this.fields = fields;
    }

    // Method to fill IsoFields from FieldValue list
    public void fillIsoFields(IsoFields isoFields) {
        isoFields.setField("0",this.caseMti);
        for (FieldValue fieldValue : this.fields) {
            FieldsDefinition fieldDef = fieldValue.getFieldDef();
            if (fieldDef != null && fieldDef.getId() != null) {
                String fieldId = String.valueOf(fieldDef.getId().getFieldId()); // Assuming fieldId is an integer or similar
                String value = fieldValue.getValue();
                isoFields.setField(fieldId, value);
            }
        }
    }
}
