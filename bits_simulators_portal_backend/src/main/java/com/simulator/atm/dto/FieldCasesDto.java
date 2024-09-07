package com.simulator.atm.dto;

import com.simulator.atm.model.ATMfield;
import com.simulator.atm.model.enm.PopularList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldCasesDto implements Serializable {
    PopularList popularList;
    String value;
    List<ATMfield> atmField;
    String bankCode;
}


