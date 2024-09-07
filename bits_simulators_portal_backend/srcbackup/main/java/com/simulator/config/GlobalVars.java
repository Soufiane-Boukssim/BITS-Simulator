package com.simulator.config;

import com.simulator.entities.UserManagement;
import com.simulator.repository.UserManagementRepository;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Field;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GlobalVars {

    @Autowired
    JwtService jwtService;
    @Autowired
    UserManagementRepository userManagementRepository;
    private static final Logger logger = LogManager.getLogger(GlobalVars.class);
    String baseUrlTest = "https://41.222.234.103:9093/NationalWallet";

    String getToken = "/getToken";
    String createWallet = "/CreateWalletWithOutOTP";
    public static String token;

    // Define headers
    HttpHeaders headers = new HttpHeaders();

    public HttpHeaders getHeaders1() {
        //logger.info(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        return headers;
    }

    public String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    public Optional<UserManagement> getUser(String token) {
        String userCode;
        if (!token.startsWith("Bearer ")) {
            userCode = jwtService.extractUsername(token);
        } else {
            String jwt = token.substring(7);
            userCode = jwtService.extractUsername(jwt);
        }
        return userManagementRepository.findById(userCode);
    }

    public Object construct(Class<?> aClass, Object dataFrom, Object dataTo) throws IllegalAccessException {
        if (aClass.isInstance(dataFrom) && aClass.isInstance(dataTo)) {
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(dataFrom);
                // Check if the value from dataFrom is null; if so, continue to the next field.
                if (value == null) {
                    continue;
                }

                field.set(dataTo, value);
            }
        }
        return dataTo;
    }








    public final static String EXCEPTIONSISSUE = "001";
    public final static String SUCCESS = "000";
    public final static String NOTEXIST = "404";
    public final static String ALREADYEXIST = "409";
    

}
