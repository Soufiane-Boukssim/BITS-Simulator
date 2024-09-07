package com.simulator.atm.services.atmm;


import com.simulator.config.GlobalVars;
import com.simulator.entities.ReasonCommandRej;
import com.simulator.entities.TerminalDefinitionId;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.atm.ReasonCommandRejRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ReasonCommandRejService {
    private static final Logger logger = LogManager.getLogger(ReasonCommandRejService.class);
    private static final String ADD_PREFIX = "ATD_";
    private static final String GET_PREFIX = "GTD_";
    private static final double RANDOM_MULTIPLIER = 9999;

    @Autowired
    private ReasonCommandRejRepository reasonCommandRejRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;

    public ResponseApiJson<?> addReasonCommandRej(ReasonCommandRej reasonCommandRej) {
        String idRequest = generateRequestId(ADD_PREFIX);
        return findByIdAndAdd(reasonCommandRej, idRequest,
                "Reason Command Rejection added successfully!",
                "This Reason Command Rejection already exists!");
    }

    public ResponseApiJson<?> getReasonCommandRej(TerminalDefinitionId id) {
        String idRequest = generateRequestId(GET_PREFIX);
        return findById(id, idRequest,
                "Get Reason Command Rejection successfully!",
                "Reason Command Rejection does not exist!");
    }

    private String generateRequestId(String prefix) {
        return prefix + new Date().getTime() + (Math.random() * RANDOM_MULTIPLIER);
    }

    private ResponseApiJson<?> findByIdAndAdd(ReasonCommandRej reasonCommandRej, String requestId,
                                              String successMessage, String failureMessage) {
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {

                reasonCommandRejRepository.save(reasonCommandRej);
                logger.info("ReasonCommandRej added: {}", reasonCommandRej);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Rejets", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert Rejets", "Rejets Inserted successfully"+reasonCommandRej.getId(), new Date());
            userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(requestId, GlobalVars.SUCCESS, successMessage);

        } catch (Exception e) {
            logger.error("Error adding ReasonCommandRej: ", e);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Rejets", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert Rejets", "An error occurred while Insert rejets ,please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(requestId, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in addReasonCommandRej");
        }
    }

    private ResponseApiJson<?> findById(TerminalDefinitionId id, String requestId,
                                        String successMessage, String failureMessage) {
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<ReasonCommandRej> reasonCommandRej = reasonCommandRejRepository.findById_BankCode(id.getBankCode());
            if (reasonCommandRej.isPresent()) {
                logger.info("ReasonCommandRej retrieved: {}", reasonCommandRej.get());

                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Rejets", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Rejets ", "Rejets retrieved successfully", new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(requestId, GlobalVars.SUCCESS, successMessage, reasonCommandRej.get());
            } else {
                return new ResponseApiJson<>(requestId, GlobalVars.NOTEXIST, failureMessage);
            }
        } catch (Exception e) {
            logger.error("Error retrieving ReasonCommandRej: ", e);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Rejets", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Rejets ", "An error occurred while retrieving rejets ,please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(requestId, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in getReasonCommandRej");
        }
    }
}
