package com.simulator.iso.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionTable {
    private Date date;
    private String reference;
    private String mti;
    private String caseName;
    private String cardProfile;
    private String bufferReq;
    private String bufferRes;
    private String mtiRes;

}
