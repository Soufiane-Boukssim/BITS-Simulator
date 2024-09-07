package com.simulator.authentification;


import com.simulator.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //private Date dateStartPass;

    //private Date dateEndPass;

    //private Character blockAccess;

    private String languageCode;

    private String mobileNumber;

    //private String accessedModules;
}