package com.simulator.authentification;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.simulator.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private String userId;
    private String userName;
    private Role userRole;
    private Character firstConnection;
    private String languageCode;
    private String bankCode;
    private String ipAddress;
}
