package com.simulator.config;

import com.simulator.authentification.AuthenticationResponse;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;

import java.util.Date;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
    private final JwtService jwtService = new JwtService();

    public ResponseApiJson<AuthenticationResponse> authenticate(UserManagement user)
            throws AuthenticationException {
        String idRequest = "VOTP_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(" authenticate ");


        logger.info("done for checks");
        var jwtToken = jwtService.generateToken(user);
        logger.info("user name " + user.getUsername());
        AuthenticationResponse response = AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .userId(user.getUserCode())
                .userName(user.getUsername())
                .build();
        return new ResponseApiJson<> (
                idRequest,
                GlobalVars.SUCCESS,
                "Connect with Success",
                response
        );
    }

    private boolean isValidLong(String code) {
        try {
            Long.parseLong(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}