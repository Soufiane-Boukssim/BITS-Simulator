package com.simulator.dtos;

import lombok.Data;
@Data

public class TerminalMessNonsoliciteDTO {
    private String terminalNumber;
    private String devicedId;
    private String diagnostique;
    private String messageUnsolicited;
    private String errorSeverity;
    private String supplyStatus;
    private String trxDeviceStatus;

}
