package com.simulator.atm.services.atmm;

import com.simulator.config.GlobalVars;
import com.simulator.dtos.TerminalMessNonsoliciteDTO;
import com.simulator.entities.TerminalMessNonSoliciteId;
import com.simulator.entities.TerminalMessNonsolicite;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.atm.TerminalMessNonsoliciteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TerminalMessNonsoliciteService {
    private static final Logger logger = LogManager.getLogger(TerminalMessNonsoliciteService.class);
    private static final String UPDATE_PREFIX = "UMN_";
    private static final String ADD_PREFIX = "AMN_";
    private static final String GET_PREFIX = "GMN_";
    private static final String DELETE_PREFIX = "DMN_";
    private static final double RANDOM_MULTIPLIER = 9999;

    @Autowired
    private TerminalMessNonsoliciteRepository terminalMessNonsoliciteRepository;


    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;

    public ResponseApiJson<?> addTerminalMessNonsolicite(TerminalMessNonsolicite terminalMessNonsolicite) {
        String idRequest = generateRequestId(ADD_PREFIX);
        return findByIdAndAdd(terminalMessNonsolicite, idRequest,
                "Inserted Terminal Mess Nonsolicite successfully!",
                "This Terminal Mess Nonsolicite already exists!");
    }



    // Methods for update and delete operations can be similarly adjusted.

    private String generateRequestId(String prefix) {
        return prefix + new Date().getTime() + (Math.random() * RANDOM_MULTIPLIER);
    }

    private ResponseApiJson<?> findByIdAndAdd(TerminalMessNonsolicite terminalMessNonsolicite, String requestId,
                                              String successMessage, String failureMessage) {
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:"+userM);
        try {
            Optional<TerminalMessNonsolicite> existingEntity = terminalMessNonsoliciteRepository.findById(terminalMessNonsolicite.getId());
            if (!existingEntity.isPresent()) {
                terminalMessNonsoliciteRepository.save(terminalMessNonsolicite);
                logger.info("TerminalMessNonsolicite added: {}", terminalMessNonsolicite);
                UserActivityTracking userTraceAdd = new UserActivityTracking(userM.get().getUserCode(),"Add", "ATM Event","000.000.00.00", "Success", GlobalVars.SUCCESS,"ATM Event","ATM Event with id : "+terminalMessNonsolicite.getId().getBankCode()+" added successfully !",new Date());
                userActivityTrackingRepository.save(userTraceAdd);
                return new ResponseApiJson<>(requestId, GlobalVars.SUCCESS, successMessage);
            } else {
                UserActivityTracking userTraceAdd = new UserActivityTracking(userM.get().getUserCode(),"Add", "ATM Event","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"ATM Event","Bank with id : "+terminalMessNonsolicite.getId().getBankCode()+" added successfully !",new Date());
                userActivityTrackingRepository.save(userTraceAdd);
                return new ResponseApiJson<>(requestId, GlobalVars.ALREADYEXIST, failureMessage);
            }
        } catch (Exception e) {
            logger.error("Error adding TerminalMessNonsolicite: ", e);
            UserActivityTracking userTraceAdd = new UserActivityTracking(userM.get().getUserCode(),"Add", "ATM Event","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"ATM Event","Bank with id : "+terminalMessNonsolicite.getId().getBankCode()+" An error occurred while add TerminalMessNonsolicite  !",new Date());
            userActivityTrackingRepository.save(userTraceAdd);
            return new ResponseApiJson<>(requestId, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in addTerminalMessNonsolicite");
        }
    }


    public ResponseApiJson<?> getTerminalMessNonsolicite(TerminalMessNonSoliciteId id) {
        String idRequest = generateRequestId(GET_PREFIX);
        return findById(id, idRequest,
                "Get Terminal Mess Nonsolicite successfully!",
                "Terminal Mess Nonsolicite not exist!");
    }

    private ResponseApiJson<List<TerminalMessNonsoliciteDTO>> findById(TerminalMessNonSoliciteId id, String requestId,
                                                                       String successMessage, String failureMessage) {
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:"+userM);
        try {
            List<TerminalMessNonsolicite> byIdBankCode = terminalMessNonsoliciteRepository.findById_BankCode(id.getBankCode());
            if (!byIdBankCode.isEmpty()) {
                List<TerminalMessNonsoliciteDTO> result = new ArrayList<>();
                for (TerminalMessNonsolicite tmn : byIdBankCode) {
                    TerminalMessNonsoliciteDTO dto = new TerminalMessNonsoliciteDTO();
                    dto.setDevicedId(tmn.getId().getDevicedId());
                    dto.setTerminalNumber(tmn.getTerminalNumber());
                    dto.setDiagnostique(tmn.getDiagnostique());
                    dto.setErrorSeverity(tmn.getErrorSeverity());
                    dto.setMessageUnsolicited(tmn.getMessageUnsolicited());
                    dto.setSupplyStatus(tmn.getSupplyStatus());
                    dto.setTrxDeviceStatus(tmn.getTrxDeviceStatus());
                    result.add(dto);
                }
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "TerminalMessNonsolicite", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve TerminalMessNonsolicite", "TerminalMessNonsolicite retrieved successfully", new Date());
                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(requestId, GlobalVars.NOTEXIST, successMessage, result);
            } else {
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "TerminalMessNonsolicite", "000.000.00.00", "Failed", GlobalVars.NOTEXIST, "Retrieve TerminalMessNonsolicite", "Failed to retrieve TerminalMessNonsolicite", new Date());
                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(requestId, GlobalVars.NOTEXIST, failureMessage);
            }
        } catch (Exception e) {
            logger.error("Error retrieving TerminalMessNonsolicite: ", e);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "TerminalMessNonsolicite", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve TerminalMessNonsolicite", "An error occurred while retrieving TerminalMessNonsolicite", new Date());
            userActivityTrackingRepository.save(userTrace);
            logger.error("Error retrieving TerminalMessNonsolicite: ", e);
            return null; // You might want to handle this case properly based on your application logic
        }
    }




//    public ResponseApiJson<?> updateTerminalMessNonsolicite( TerminalMessNonsolicite terminalMessNonsolicite) {
//        String idRequest = generateRequestId(UPDATE_PREFIX);
//        try {
//            Optional <TerminalMessNonsolicite> existingEntity = terminalMessNonsoliciteRepository.findById(terminalMessNonsolicite.getId());
//
//            logger.info("---->existingEntity",existingEntity);
//            if (existingEntity.isPresent()) {
//                terminalMessNonsoliciteRepository.save(terminalMessNonsolicite);
//                logger.info("TerminalMessNonsolicite updated: {}", terminalMessNonsolicite);
//                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Terminal Mess Nonsolicite updated successfully!");
//            } else {
//                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Terminal Mess Nonsolicite does not exist!");
//            }
//        } catch (Exception e) {
//            logger.error("Error updating TerminalMessNonsolicite: ", e);
//            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in updateTerminalMessNonsolicite");
//        }
//    }




//    public ResponseApiJson<?> updateTerminalMessNonsolicite(List<TerminalMessNonsolicite> terminalMessNonsolicite) {
//        String idRequest = generateRequestId(UPDATE_PREFIX);
//        Optional<TerminalMessNonsolicite> existingEntity;
//        try {
//            boolean allUpdated = true;
//            for (TerminalMessNonsolicite Terminal : terminalMessNonsolicite) {
//                existingEntity = terminalMessNonsoliciteRepository.findById(Terminal.getId());
//                if (existingEntity.isPresent()) {
//                    logger.info("---->Terminal:"+Terminal);
//                    terminalMessNonsoliciteRepository.save(Terminal);
//                    logger.info("TerminalMessNonsolicite updated: {}", Terminal);
//                    return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Terminal Mess Nonsolicite updated successfully!");
//                } else {
//                    return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Terminal Mess Nonsolicite does not exist!");
//                }
//            }
//        } catch (Exception e) {
//            logger.error("Error updating TerminalMessNonsolicite: ", e);
//            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in updateTerminalMessNonsolicite");
//        }
//        // Missing return statement
//        return null; // or return something appropriate based on your logic
//    }


    public ResponseApiJson<?> updateTerminalMessNonsolicite(List<TerminalMessNonsolicite> terminalMessNonsolicite) {
        String idRequest = generateRequestId(UPDATE_PREFIX);
        Optional<TerminalMessNonsolicite> existingEntity;
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:"+userM);
        try {
            boolean allUpdated = true; // Variable pour suivre si tous les éléments ont été mis à jour avec succès
            for (TerminalMessNonsolicite Terminal : terminalMessNonsolicite) {
                existingEntity = terminalMessNonsoliciteRepository.findById(Terminal.getId());
                if (existingEntity.isPresent()) {
                    terminalMessNonsoliciteRepository.save(Terminal);
                    logger.info("TerminalMessNonsolicite updated: {}", Terminal);

                    UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"update", "ATM Event","000.000.00.00", "Success", GlobalVars.SUCCESS,"ATM Event","Bank with id : "+Terminal.getId().getBankCode()+" added successfully !",new Date());
                    userActivityTrackingRepository.save(userTrace);

                } else {
                    // Si un élément n'existe pas, marquez que tous les éléments n'ont pas été mis à jour avec succès
                    terminalMessNonsoliciteRepository.save(Terminal);
                    logger.info("---->Terminal n'existent pas :"+Terminal);
                    UserActivityTracking userTraceAdd = new UserActivityTracking(userM.get().getUserCode(),"Add", "ATM Event","000.000.00.00", "Success", GlobalVars.SUCCESS,"ATM Event","Bank with id : "+Terminal.getId().getBankCode()+" added successfully !",new Date());
                    userActivityTrackingRepository.save(userTraceAdd);
                    allUpdated = true;
                }
            }
            // Retournez la réponse en fonction de si tous les éléments ont été mis à jour avec succès ou non
            if (allUpdated) {
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Tous les Terminaux Mess Nonsolicite ont été mis à jour avec succès!");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Certains Terminaux Mess Nonsolicite n'existent pas!");
            }
        } catch (Exception e) {
            logger.error("Error updating TerminalMessNonsolicite: ", e);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"update", "ATM Event","000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE,"Update Event","An error occurred while updating Terminal with ID : ",new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in updateTerminalMessNonsolicite");
        }
    }



    public ResponseApiJson<?> deleteTerminalMessNonsolicite(TerminalMessNonSoliciteId id) {
        String idRequest = generateRequestId(DELETE_PREFIX);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:" + userM);
        Optional<TerminalMessNonsolicite> terminalMessNonsolicite = null;
        try {
            terminalMessNonsolicite = terminalMessNonsoliciteRepository.findById(id);
            if (terminalMessNonsolicite.isPresent()) {
                terminalMessNonsoliciteRepository.delete(terminalMessNonsolicite.get());
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Bank", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete  ATM Event", "ATM Event with id : " + terminalMessNonsolicite.get().getId().getBankCode() + " DElete successfully !", new Date());
                userActivityTrackingRepository.save(userTrace);
                logger.info("TerminalMessNonsolicite deleted: {}", id);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Terminal Mess Nonsolicite deleted successfully!");
            } else {
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Bank", "000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE, "Delete  ATM Event", "ATM Event with id : " + terminalMessNonsolicite.get().getId().getBankCode() + " Delete Failed !", new Date());
                userActivityTrackingRepository.save(userTrace);
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Terminal Mess Nonsolicite does not exist!");
            }
        } catch (Exception e) {
            logger.error("Error deleting TerminalMessNonsolicite: ", e);
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "Bank", "000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE, "Delete  ATM Event", "ATM Event with id : " + terminalMessNonsolicite.get().getId().getBankCode() + "Please check with your system administrator!", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Exception occurred in deleteTerminalMessNonsolicite");
        }
    }
}
