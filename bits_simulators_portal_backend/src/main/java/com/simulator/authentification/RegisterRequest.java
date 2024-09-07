package com.simulator.authentification;


import com.simulator.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String userCode;
    private String email;
    private String password;
    private Role userType;

    private String userBankCode;

    private String address;

    //private String ipAddressMang;

    private String ipAddress;

    //private Integer passwordExpiration;

    private Date dateStartPass;

    private Date dateEndPass;

    //private Character blockAccess;
    // 12.02.2024 HAMZA
    private int numberOfTriesAllowed;

    private int numberOfTries;
    private String status;

    private String userName;

    private Character blockAccess;

    // end 12.02.2024 HAMZA
    private String languageCode;

    private String mobileNumber;

    //private String accessedModules;
}