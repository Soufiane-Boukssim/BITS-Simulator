package com.simulator.models;

import lombok.Data;

@Data
public class UpdatePasswordRequest {

    private String userCode;
    private String oldPassword;
    private String newPassword;

}
