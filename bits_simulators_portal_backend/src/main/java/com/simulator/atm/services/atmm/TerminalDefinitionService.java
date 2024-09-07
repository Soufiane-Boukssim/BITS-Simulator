package com.simulator.atm.services.atmm;

import com.simulator.atm.model.DefinitionModel;
import com.simulator.config.GlobalVars;
import com.simulator.entities.*;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.atm.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TerminalDefinitionService {
    private static final Logger logger = LogManager.getLogger(TerminalDefinitionService.class);
//    private static final String UPDATE_PREFIX = "UTD_";
    private static final String ADD_PREFIX = "ATD_";
    private static final String GET_PREFIX = "GTD_";
//    private static final String DELETE_PREFIX = "DTD_";
    private static final double RANDOM_MULTIPLIER = 9999;

    @Autowired
    private TerminalDefinitionRepository terminalDefinitionRepository;
    @Autowired
    private TerminalCashRepository terminalCashRepository;
    @Autowired
    private TerminalPrinterRepository terminalPrinterRepository;
    @Autowired
    private TerminalConfigurationRepository terminalConfigurationRepository;
    @Autowired
    private TerminalKeysRepository terminalKeysRepository;


    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVarsTracking;

    /*public ResponseApiJson<?> updateComms(CommsDefinition commsDefinition) {
        String idRequest = generateRequestId(UPDATE_PREFIX);
        return findByIdAndUpdate(commsDefinition, idRequest,
                "Update Comms successful!",
                "This Comms does not exist!");
    }*/

    public ResponseApiJson<?> addTerminalDefinition(DefinitionModel definitionModel) {
        String idRequest = generateRequestId(ADD_PREFIX);
        return findByIdAndAdd(definitionModel, idRequest,
                "Inserted terminal definition successfully!",
                "This terminal definition already exists!");
    }

    public ResponseApiJson<?> getTerminalDefinition(TerminalDefinitionId terminalDefinitionId) {
        String idRequest = generateRequestId(GET_PREFIX);
        return findById(terminalDefinitionId, idRequest,
                "Get terminal definition successfully!",
                "Terminal definition not exist!");
    }

    /*public ResponseApiJson<?> deleteComms(CommsDefinitionId id) {
        String idRequest = generateRequestId(DELETE_PREFIX);
        return deleteById(id, idRequest,
                "Delete communication successfully!",
                "Communication not exist!");
    }*/

    private String generateRequestId(String prefix) {
        return prefix + new Date().getTime() + (Math.random() * RANDOM_MULTIPLIER);
    }

    /*private ResponseApiJson<?> findByIdAndUpdate(CommsDefinition commsDefinition, String requestId,
                                                 String successMessage, String failureMessage) {
        try {
            Optional<CommsDefinition> existingEntity = commsDefinitionRepository.findById(commsDefinition.getId());
            if (existingEntity.isPresent()) {
                CommsDefinition updatedEntity = (CommsDefinition) globalVars.construct(CommsDefinition.class, commsDefinition, existingEntity.get());
                commsDefinitionRepository.save(updatedEntity);
                logger.info("TerminalDefinition updated: {}", updatedEntity);
                return new ResponseApiJson<>(requestId, GlobalVars.SUCCESS, successMessage);
            } else {
                return new ResponseApiJson<>(requestId, GlobalVars.NOTEXIST, failureMessage);
            }
        } catch (Exception e) {
            logger.error("Error updating CommsDefinition: ", e);
            return new ResponseApiJson<>(requestId, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in updateComms");
        }
    }*/

    private ResponseApiJson<?> findByIdAndAdd(DefinitionModel terminalDefinition, String requestId,
                                              String successMessage, String failureMessage) {
        Optional<UserManagement> userM = globalVarsTracking.getUser(GlobalVars.getBearerTokenHeader());
        try {

                terminalDefinitionRepository.save(terminalDefinition.getTerminalDefinition());

                terminalCashRepository.save(terminalDefinition.getNote());



                terminalPrinterRepository.save(terminalDefinition.getPrinter());



                terminalConfigurationRepository.save(terminalDefinition.getConfiguration());



                terminalKeysRepository.save(terminalDefinition.getKeys());

            logger.info("TerminalDefinition updated: {}", terminalDefinition);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Terminal Definition", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert Terminal Definition", "Terminal Definition Insert successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(requestId, GlobalVars.SUCCESS, successMessage);
        } catch (Exception e) {
            logger.error("Error adding TerminalDefinition: ", e);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Terminal Definition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert Terminal Definition", "An error occurred while Insert Terminal Definition", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(requestId, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in add Terminal");
        }
    }

    private ResponseApiJson<?> findById(TerminalDefinitionId terminalDefinitionId, String requestId,
                                        String successMessage, String failureMessage) {
        Optional<UserManagement> userM = globalVarsTracking.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<TerminalDefinition> terminalDefinition = terminalDefinitionRepository.findById( new TerminalDefinitionId( "00000001",terminalDefinitionId.getBankCode()));
            Optional<TerminalCash> terminalCash = terminalCashRepository.findById( new TerminalDefinitionId( "00000001",terminalDefinitionId.getBankCode()));
            Optional<TerminalPrinter> terminalPrinter = terminalPrinterRepository.findById( new TerminalDefinitionId( "00000001",terminalDefinitionId.getBankCode()));
            Optional<TerminalConfiguration> terminalConfiguration = terminalConfigurationRepository.findById( new TerminalDefinitionId( "00000001",terminalDefinitionId.getBankCode()));
            Optional<TerminalKeys> terminalKeys = terminalKeysRepository.findById( new TerminalDefinitionId( "00000001",terminalDefinitionId.getBankCode()));
            DefinitionModel definitionModel=new DefinitionModel();
            terminalDefinition.ifPresent(definitionModel::setTerminalDefinition);
            terminalCash.ifPresent(definitionModel::setNote);
            terminalPrinter.ifPresent(definitionModel::setPrinter);
            terminalConfiguration.ifPresent(definitionModel::setConfiguration);
            terminalKeys.ifPresent(definitionModel::setKeys);

                logger.info("TerminalDefinition retrieved: {}", definitionModel);

                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Terminal Definition", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve Terminal Definition", "Terminal Definition retrieved successfully", new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(requestId, GlobalVars.SUCCESS, successMessage,definitionModel);

        } catch (Exception e) {
            logger.error("Error retrieving TerminalDefinition: ", e);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Terminal Definition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve Terminal Definition", "An error occurred while retrieving Terminal Definition", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(requestId, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in get terminal definition");
        }
    }

    /*private ResponseApiJson<?> deleteById(CommsDefinitionId id, String requestId,
                                          String successMessage, String failureMessage) {
        try {
            Optional<CommsDefinition> commsDefinition = commsDefinitionRepository.findById(id);
            if (commsDefinition.isPresent()) {
                commsDefinitionRepository.delete(commsDefinition.get());
                logger.info("CommsDefinition deleted: {}", id);
                return new ResponseApiJson<>(requestId, GlobalVars.SUCCESS, successMessage);
            } else {
                return new ResponseApiJson<>(requestId, GlobalVars.NOTEXIST, failureMessage);
            }
        } catch (Exception e) {
            logger.error("Error deleting CommsDefinition: ", e);
            return new ResponseApiJson<>(requestId, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in deleteComms");
        }
    }*/

    // Assume GlobalVars is a class with a construct method used for entity creation or update
    private GlobalVars globalVars = new GlobalVars();

}

