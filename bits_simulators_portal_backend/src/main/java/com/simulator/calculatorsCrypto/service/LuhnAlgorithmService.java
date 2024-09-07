package com.simulator.calculatorsCrypto.service;


import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LuhnAlgorithmService {

    private final Logger logger = LogManager.getLogger(LuhnAlgorithmService.class);


    public ResponseApiJson<?> luhnAlgorithmGenerate(String pan){
        String idRequest = "PIN_G" + new Date().getTime() + (Math.random() * 9999);
        try {
            int checkDigit = generateLuhnCheckDigit(pan);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Luhn check digit is " + checkDigit, checkDigit);
        }
        catch (Exception e) {
            logger.info("Exception of this::luhnAlgorithm " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in Luhn algorithm, please check with your provider!");
        }
    }


    private int generateLuhnCheckDigit(String number) {
        int total = 0;
        boolean even = true;
        // iterate from right to left, double every 'even' value
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = number.charAt(i) - '0';
            if (digit < 0 || digit > 9) {
                // value may only contain digits
                throw new IllegalArgumentException("Invalid character in PAN");
            }
            if (even) {
                digit <<= 1; // double value
            }
            even = !even;
            total += digit > 9 ? digit - 9 : digit;
        }
        // find the check digit that makes the total a multiple of 10
        int checkDigit = (total * 9) % 10;
        return checkDigit;
    }


    public ResponseApiJson<?> luhnAlgorithmValidate(String pan){
        String idRequest = "PIN_V" + new Date().getTime() + (Math.random() * 9999);
        try {
            boolean isValid = isValidLuhn(pan);
            return new ResponseApiJson<>(idRequest, isValid ?  GlobalVars.SUCCESS :  GlobalVars.NOTEXIST, "Luhn validation result: " + (isValid ? "Valid" : "Invalid"), isValid);
        }
        catch (Exception e) {
            logger.info("Exception in this::luhnAlgorithmValidate " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in Luhn algorithm, please check with your provider!");
        }
    }

    public static boolean isValidLuhn(String number) {
        int n = number.length();
        int total = 0;
        boolean even = true;
        // iterate from right to left, double every 'even' value
        for (int i = n - 2; i >= 0; i--) {
            int digit = number.charAt(i) - '0';
            if (digit < 0 || digit > 9) {
                // value may only contain digits
                return false;
            }
            if (even) {
                digit <<= 1; // double value
            }
            even = !even;
            total += digit > 9 ? digit - 9 : digit;
        }
        int checksum = number.charAt(n - 1) - '0';
        return (total + checksum) % 10 == 0;
    }


}
