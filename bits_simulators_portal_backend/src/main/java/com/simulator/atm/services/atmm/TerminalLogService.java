package com.simulator.atm.services.atmm;

import com.simulator.atm.controllers.controlleratm.TerminalLogController;
import com.simulator.atm.repositories.repoatm.TerminalLogRepository;
import com.simulator.config.GlobalVars;
import com.simulator.entities.*;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.globalService.AuthenticationService;
import com.simulator.repository.ProtocoleRepository;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.UserHabilitationParamRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TerminalLogService {
    @Autowired
    TerminalLogRepository terminalLogRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    ProtocoleRepository protocoleRepository;



    @Autowired
    GlobalVars globalVars;

    @Autowired
    UserHabilitationParamRepository userHabilitationParamRepository;
    private JSONObject joResp = new JSONObject();
    private static final int MAX_LOG_SEQUENCE_LENGTH = 5;

    private final Logger logger = LogManager.getLogger(TerminalLogController.class);

   /* public ResponseApiJson<List<TerminalLog>> getTerminalLogs(String bankCode,String dateFromStr ,String dateToStr ) {
        String idRequest = "TERMINAL_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:"+userM);
        try {
//            List<TerminalLog> data = terminalLogRepository.findAll();
            List<TerminalLog> data = terminalLogRepository.findById_BankCode_date(bankCode,dateFromStr,dateToStr);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "logs", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve logs", "logs retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting logs successful", data);

        } catch (Exception ex) {
            logger.error("Exception in getTerminalLogs: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "logs", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve logs", "An error occurred while retrieving logs", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getTerminalLogs, please check with your provider !");
        }
    }*/


    public ResponseApiJson<List<TerminalLog>> getTerminalLogs(String bankCode, String dateFromStr, String dateToStr) {
        String idRequest = "TERMINAL_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:" + userM);

        List<TerminalLog> data;
        try {
            if (dateFromStr == null || dateFromStr.isEmpty() || dateToStr == null || dateToStr.isEmpty()) {
                // If dateFromStr and dateToStr are not provided, get all logs for the bankCode
                data = terminalLogRepository.findByBankCode(bankCode);
            } else {
                data = terminalLogRepository.findByBankCodeAndDateRange(bankCode, dateFromStr, dateToStr);

            }

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "logs", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve logs", "Logs retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting logs successful", data);

        } catch (Exception ex) {
            logger.error("Exception in getTerminalLogs: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "logs", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve logs", "An error occurred while retrieving logs", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getTerminalLogs, please check with your provider!");
        }
    }
    public ResponseApiJson<?> saveMessages(String messageInData, String messageOutData) {
        UserManagement currentUser = authenticationService.getCurrentUser();
        String idRequest = "Show_Details" + new Date().getTime() + (Math.random() * 9999);

        try{

            TerminalLog terminalLog = new TerminalLog();

            logger.info("messageInData: " + messageInData);
            String logRDateHexAscii = convertHexAsciiToText(messageInData);
            logger.info("logRDateHexAscii: " + logRDateHexAscii);

            terminalLog.setLogRData(logRDateHexAscii); // Message In
            terminalLog.setLogEData(messageOutData); // Message Out

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            terminalLog.setLogRDate(formatter.format(date)); // Date for Message In
            terminalLog.setLogEDate(formatter.format(date)); // Date for Message Out

            TerminalParamLogId id = new TerminalParamLogId();
            id.setTermCode(UUID.randomUUID().toString().substring(0, MAX_LOG_SEQUENCE_LENGTH));
            id.setBankCode(currentUser.getUserBankCode());
            terminalLog.setId(id);


            String logSequence = UUID.randomUUID().toString();
            if (logSequence.length() > MAX_LOG_SEQUENCE_LENGTH) {
                logSequence = logSequence.substring(0, MAX_LOG_SEQUENCE_LENGTH);
            }
            terminalLog.setLogSequence(logSequence);

            TerminalLog dataSeved=terminalLogRepository.save(terminalLog);


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "message save successfully", dataSeved);

        }catch (Exception e){
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "An error occurred while saveMessages", "");

        }

    }



    private String convertHexAsciiToText(String hex) {
        StringBuilder output = new StringBuilder();

        // Ensure the hex string has an even length
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }

        return output.toString();
    }


    public ResponseApiJson<List<UserHabilitationParam>> getUserRolePage(String user_code ) {
        String idRequest = "UserRolePage_" + new Date().getTime() + (Math.random() * 9999);

        try {
            logger.info("---->user_code :"+user_code);
            List<UserHabilitationParam> data = userHabilitationParamRepository.findByUserCode(user_code);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting UserRolePage_ successful", data);

        } catch (Exception ex) {
            logger.error("Exception in UserRolePage_: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in UserRolePage, please check with your provider !");
        }
    }



    public ResponseApiJson<List<Protocole>> getAllProtocol() {
        String idRequest = "Protocoles" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<Protocole> data = protocoleRepository.findAll();

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting Protocoles successful", data);

        } catch (Exception ex) {
            logger.error("Exception in Protocoles: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in Protocoles, please check with your provider !");
        }
    }


}
