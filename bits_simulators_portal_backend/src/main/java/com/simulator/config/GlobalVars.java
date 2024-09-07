package com.simulator.config;

import com.simulator.entities.CasesDefinition;
import com.simulator.entities.FieldValue;
import com.simulator.entities.UserManagement;
import com.simulator.entities.pos.PosCasesDefinition;
import com.simulator.entities.pos.PosFieldValue;
import com.simulator.repository.CurrencyRepository;
import com.simulator.repository.UserManagementRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class GlobalVars {


    JwtService jwtService = new JwtService();
    @Autowired
    UserManagementRepository userManagementRepository ;
    @Autowired
    CurrencyRepository currencyRepository;

    private Map<String, String> sessionMap = new HashMap<>();
    public Map<String, String> getSessionMap() {
        return sessionMap;
    }


    private static final Logger logger = LogManager.getLogger(GlobalVars.class);
    public static String baseUrlTest = "https://acs.bits.ma:13443/BITSWS/";

    public static String baseUrlLocal = "http://localhost:8080/BITSWS/";

    public static String sidBuildMsg = "/SidBuildMsg";
    public static String SidDump = "/SidDump";
    public static String VisaDump = "/VisaDump";
    public static String VisaBuildMsgkeyExchange = "/VisaBuildMsgkeyExchange";
    public static String MasterCardDump = "/MasterCardDump";
    public static String visaBuildMsg = "/VisaBuildMsg";
    public static String masterBuildMsg = "/MasterBuildMsg";


    public static String SidMsgHexTrace = "/SidMsgHexTrace";
    public static String VisaMsgHexTrace = "/VisaMsgHexTrace";
    public static String MasterCardMsgHexTrace = "/MasterCardMsgHexTrace";

    public static String token;

    // Define headers
    HttpHeaders headers = new HttpHeaders();

    public HttpHeaders getHeaders1() {
        //logger.info(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        return headers;
    }




    private static final int DEFAULT_ID = 504;
    private static final String DEFAULT_AMOUNT = "0";

    private static final int FIELD_ID_CASE_4 = 49;
    private static final int AMOUNT_FIELD_ID_CASE_4 = 4;
    private static final int FIELD_ID_CASE_5 = 50;
    private static final int AMOUNT_FIELD_ID_CASE_5 = 5;
    private static final int FIELD_ID_CASE_6 = 51;
    private static final int AMOUNT_FIELD_ID_CASE_6 = 6;


    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public double isoToRealAmount(CasesDefinition casesDefinition, int i){
        try {
            logger.info("case Definition[" + casesDefinition);
            return calculateAmountBasedOnCase(casesDefinition, i);
        } catch (Exception e) {
            logger.error("Exception in getAmount: " + e.getMessage());
            return 0.0;
        }
    }

    private double calculateAmountBasedOnCase(CasesDefinition casesDefinition, int i) {
        Optional<FieldValue> fieldValueDTO1;
        Optional<FieldValue> fieldValueDTO2;
        int fieldId = 0;
        int amountFieldId = 0;

        switch(i) {
            case 4:
                fieldId = FIELD_ID_CASE_4;
                amountFieldId = AMOUNT_FIELD_ID_CASE_4;
                break;
            case 5:
                fieldId = FIELD_ID_CASE_5;
                amountFieldId = AMOUNT_FIELD_ID_CASE_5;
                break;
            case 6:
                fieldId = FIELD_ID_CASE_6;
                amountFieldId = AMOUNT_FIELD_ID_CASE_6;
                break;
            default:
                logger.warn("Invalid case: " + i);
                return 0.0;
        }

        fieldValueDTO1 = findFieldValue(casesDefinition, fieldId);
        fieldValueDTO2 = findFieldValue(casesDefinition, amountFieldId);

        String id = fieldValueDTO1.map(FieldValue::getValue).orElse(String.valueOf(DEFAULT_ID));
        String amount = fieldValueDTO2.map(FieldValue::getValue).orElse(DEFAULT_AMOUNT);
        logger.info("Calculated amount for case " + id + ": " + amount);
        int exponent = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency ID: " + id))
                .getCcyExponent();

        double v = (Double.parseDouble(amount))/(Math.pow(10.0, exponent));
        logger.info("Calculated amount for case " + i + ": %.2f" + v);
        return v;
    }

    private Optional<FieldValue> findFieldValue(CasesDefinition casesDefinition, int fieldId) {
        return casesDefinition.getFields().stream()
                .filter(fieldValueDTO -> fieldValueDTO.getFieldDef().getId().getFieldId() == fieldId)
                .findAny();
    }



    public String realToIsoAmount(CasesDefinition casesDefinition, int i, double realAmount){
        try {
            logger.info("case Definition[" + casesDefinition + "] with realAmount: " + realAmount);
            double isoAmount = calculateIsoAmountBasedOnCase(casesDefinition, i, realAmount);
            return formatIsoAmount(isoAmount);
        } catch (Exception e) {
            logger.error("Exception in realToIsoAmount: " + e.getMessage());
            return "000000000000"; // Return a string of 12 zeros in case of an error.
        }
    }

    private double calculateIsoAmountBasedOnCase(CasesDefinition casesDefinition, int i, double realAmount) {
        Optional<FieldValue> fieldValueDTO;
        int fieldId = 0;

        switch(i) {
            case 4:
                fieldId = FIELD_ID_CASE_4;
                break;
            case 5:
                fieldId = FIELD_ID_CASE_5;
                break;
            case 6:
                fieldId = FIELD_ID_CASE_6;
                break;
            default:
                logger.warn("Invalid case: " + i);
                return 0.0;
        }

        fieldValueDTO = findFieldValue(casesDefinition, fieldId);

        String id = fieldValueDTO.map(FieldValue::getValue).orElse(String.valueOf(DEFAULT_ID));
        int exponent = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency ID: " + id))
                .getCcyExponent();

        double isoAmount = calculateIsoAmount(realAmount, exponent);
        logger.info("Calculated ISO amount for case " + i + ": " + isoAmount);
        return isoAmount;
    }



    //pos

    public String realToIsoAmountPOS(PosCasesDefinition casesDefinition, int i, double realAmount){
        try {
            logger.info("case Definition[" + casesDefinition + "] with realAmount: " + realAmount);
            double isoAmount = calculateIsoAmountBasedOnCasePOS(casesDefinition, i, realAmount);
            return formatIsoAmount(isoAmount);
        } catch (Exception e) {
            logger.error("Exception in realToIsoAmount: " + e.getMessage());
            return "000000000000"; // Return a string of 12 zeros in case of an error.
        }
    }

    private double calculateIsoAmountBasedOnCasePOS(PosCasesDefinition casesDefinition, int i, double realAmount) {
        Optional<PosFieldValue> fieldValueDTO;
        int fieldId = 0;

        switch(i) {
            case 4:
                fieldId = FIELD_ID_CASE_4;
                break;
            case 5:
                fieldId = FIELD_ID_CASE_5;
                break;
            case 6:
                fieldId = FIELD_ID_CASE_6;
                break;
            default:
                logger.warn("Invalid case: " + i);
                return 0.0;
        }

        fieldValueDTO = findFieldValuePos(casesDefinition, fieldId);

        String id = fieldValueDTO.map(PosFieldValue::getValue).orElse(String.valueOf(DEFAULT_ID));
        int exponent = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency ID: " + id))
                .getCcyExponent();

        double isoAmount = calculateIsoAmount(realAmount, exponent);
        logger.info("Calculated ISO amount for case " + i + ": " + isoAmount);
        return isoAmount;
    }

    //
    private double calculateIsoAmount(double realAmount, int exponent) {
        // Reverse calculation logic, modify as needed based on actual conversion formula
        return realAmount * Math.pow(10, exponent);
    }

    private String formatIsoAmount(double isoAmount) {
        return String.format("%012.0f", isoAmount);
    }




    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    public Optional<UserManagement> getUser(String token) {
        String userCode;
        if (!token.startsWith("Bearer ")) {
            userCode = jwtService.extractUsername(token);
        } else {
            String jwt = token.substring(7);
            logger.info("get User token  " + jwt);
            userCode = this.jwtService.extractUsername(jwt);
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
                /*if (value == null) {
                    continue;
                }*/

                field.set(dataTo, value);
            }
        }
        return dataTo;
    }







    public double posToRealAmount(PosCasesDefinition casesDefinition, int i){
        try {
            logger.info("case Definition[" + casesDefinition);
            return calculateAmountBasedOnCasePos(casesDefinition, i);
        } catch (Exception e) {
            logger.error("Exception in getAmount: " + e.getMessage());
            return 0.0;
        }
    }

    private double calculateAmountBasedOnCasePos(PosCasesDefinition casesDefinition, int i) {
        Optional<PosFieldValue> fieldValueDTO1;
        Optional<PosFieldValue> fieldValueDTO2;
        int fieldId = 0;
        int amountFieldId = 0;

        switch(i) {
            case 4:
                fieldId = FIELD_ID_CASE_4;
                amountFieldId = AMOUNT_FIELD_ID_CASE_4;
                break;
            case 5:
                fieldId = FIELD_ID_CASE_5;
                amountFieldId = AMOUNT_FIELD_ID_CASE_5;
                break;
            case 6:
                fieldId = FIELD_ID_CASE_6;
                amountFieldId = AMOUNT_FIELD_ID_CASE_6;
                break;
            default:
                logger.warn("Invalid case: " + i);
                return 0.0;
        }

        fieldValueDTO1 = findFieldValuePos(casesDefinition, fieldId);
        fieldValueDTO2 = findFieldValuePos(casesDefinition, amountFieldId);

        String id = fieldValueDTO1.map(PosFieldValue::getValue).orElse(String.valueOf(DEFAULT_ID));
        String amount = fieldValueDTO2.map(PosFieldValue::getValue).orElse(DEFAULT_AMOUNT);
        logger.info("Calculated amount for case " + id + ": " + amount);
        int exponent = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency ID: " + id))
                .getCcyExponent();

        double v = (Double.parseDouble(amount))/(Math.pow(10.0, exponent));
        logger.info("Calculated amount for case " + i + ": %.2f" + v);
        return v;
    }

    private Optional<PosFieldValue> findFieldValuePos(PosCasesDefinition casesDefinition, int fieldId) {
        return casesDefinition.getFields().stream()
                .filter(fieldValueDTO -> fieldValueDTO.getFieldDef().getId().getFieldId() == fieldId)
                .findAny();
    }



    public String realToPosAmount(PosCasesDefinition casesDefinition, int i, double realAmount){
        try {
            logger.info("case Definition[" + casesDefinition + "] with realAmount: " + realAmount);
            double isoAmount = calculatePosAmountBasedOnCase(casesDefinition, i, realAmount);
            return formatIsoAmount(isoAmount);
        } catch (Exception e) {
            logger.error("Exception in realToIsoAmount: " + e.getMessage());
            return "000000000000"; // Return a string of 12 zeros in case of an error.
        }
    }

    private double calculatePosAmountBasedOnCase(PosCasesDefinition casesDefinition, int i, double realAmount) {
        Optional<PosFieldValue> fieldValueDTO;
        int fieldId = 0;

        switch(i) {
            case 4:
                fieldId = FIELD_ID_CASE_4;
                break;
            case 5:
                fieldId = FIELD_ID_CASE_5;
                break;
            case 6:
                fieldId = FIELD_ID_CASE_6;
                break;
            default:
                logger.warn("Invalid case: " + i);
                return 0.0;
        }

        fieldValueDTO = findFieldValuePos(casesDefinition, fieldId);

        String id = fieldValueDTO.map(PosFieldValue::getValue).orElse(String.valueOf(DEFAULT_ID));
        int exponent = currencyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid currency ID: " + id))
                .getCcyExponent();

        double isoAmount = calculateIsoAmount(realAmount, exponent);
        logger.info("Calculated ISO amount for case " + i + ": " + isoAmount);
        return isoAmount;
    }






    public final static String EXCEPTIONSISSUE = "001";
    public final static String SUCCESS = "000";
    public final static String NOTEXIST = "404";
    public final static String ALREADYEXIST = "409";
    

}
