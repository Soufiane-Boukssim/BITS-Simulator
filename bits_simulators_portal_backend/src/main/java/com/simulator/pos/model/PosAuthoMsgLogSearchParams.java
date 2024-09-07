package com.simulator.pos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosAuthoMsgLogSearchParams {

    private String dateFromStr;
    private String dateToStr;
    private String protocol;
    private String instCode;
}
