package com.simulator.pos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosExecutionTable {
    private Date date;
    private String reference;
    private String mti;
    private String caseName;
    private String cardProfile;
    private String bufferReq;
    private String bufferRes;
    //private String Amount;

}
