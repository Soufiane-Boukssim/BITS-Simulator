package com.simulator.smartICC.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GlobalResponse<T> {
    private String RespCode;
    private String RespMsg;
    private T Result;

    public GlobalResponse(String RespCode, String RespMsg) {
        this.RespCode = RespCode;
        this.RespMsg = RespMsg;
    }
}