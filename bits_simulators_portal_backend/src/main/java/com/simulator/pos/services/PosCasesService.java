package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.dtos.pos.PosCasesDefDTO;
import com.simulator.dtos.pos.PosCasesDefinitionDTO;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.entities.pos.PosCasesDefinition;
import com.simulator.entities.pos.PosCasesDefinitionId;
import com.simulator.entities.pos.PosFieldValue;
import com.simulator.entities.pos.PosFieldsDefinition;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.pos.PosCasesRepository;
import com.simulator.repository.pos.PosFieldsDefinitionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.simulator.config.GlobalVars.isDouble;


@Service
public  class PosCasesService {
    private final Logger logger = LogManager.getLogger(PosCasesService.class);
    private final PosCasesRepository casesRepository;
    private final PosFieldsDefinitionRepository fieldDefinitionRepo;
    private final PosSubFieldParsing posSubFieldParsing;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;


    @Autowired
    public PosCasesService(PosCasesRepository casesRepository, PosFieldsDefinitionRepository fieldDefinitionRepo, PosSubFieldParsing posSubFieldParsing) {
        this.casesRepository = casesRepository;
        this.fieldDefinitionRepo = fieldDefinitionRepo;
        this.posSubFieldParsing = posSubFieldParsing;
    }

    public ResponseApiJson<List<PosCasesDefDTO>> getAllCasesDefinitions(PosCasesDefinitionId casesDefinitionId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<Object[]> queryResult = casesRepository.findByBankCodeAndCaseProtocole(casesDefinitionId.getBankCode(), casesDefinitionId.getCaseProtocole());
//            for (Object[] a : queryResult) {
//                logger.info(Arrays.toString(a));
//            }
            List<PosCasesDefDTO> casesDefList = queryResult.stream()
                    .map(result -> new PosCasesDefDTO(
                            (String) result[0],       // Assuming caseNo is a String
                            (String) result[3],       // Assuming caseDesc is a String
                            (String) result[1],       // Assuming bankCode is a String
                            (String) result[2]     // Assuming caseProtocole is a Character
                    ))
                    .collect(Collectors.toList());

            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "casesDefintion", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve casesDefintion", "casesDefintion retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Success!", casesDefList);
        } catch (Exception e) {
            logger.error("Exception in getAllCasesDefinitions : " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "casesDefintion", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve casesDefintion", "some issues in getAllcasesDefintions please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Exception when getting casesDefintion records");
        }
    }

    public ResponseApiJson<Map<String, PosCasesDefinitionDTO>> getCaseDefinition(PosCasesDefinitionId casesDefinitionId) {
        String idRequest = "GC_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosCasesDefinition> cases = casesRepository.findById_CaseNoAndId_BankCodeAndId_CaseProtocole(
                    casesDefinitionId.getCaseNo(),
                    casesDefinitionId.getBankCode(),
                    casesDefinitionId.getCaseProtocole()
            );
            // Convert ISO amounts to real amounts for fields 4, 5, and 6
            List<PosCasesDefinition> lastCases = cases.stream()
                    .map(this::convertFieldAmount)
                    .toList();
            List<PosCasesDefinitionDTO> casesDefs = posSubFieldParsing.processFields(cases);

            logger.info(casesDefs.size());
            Map<String, PosCasesDefinitionDTO> casesMap = casesDefs.stream()
                    .filter(caseDef -> 'I' == caseDef.id().getCaseType() || 'O' == caseDef.id().getCaseType())
                    .collect(Collectors.toMap(
                            caseDef -> 'I' == caseDef.id().getCaseType() ? "in" : "out",
                            caseDef -> caseDef
                    ));
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Success!", casesMap);
        } catch (Exception e) {
            logger.error("Exception in getAllCasesDefinitions : " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Exception when getting casesDefintion records");
        }
    }

    private PosCasesDefinition convertFieldAmount(PosCasesDefinition caseDef) {
        caseDef.getFields().forEach(fieldValue -> {
            for (int fieldId = 4; fieldId <= 6; fieldId++) {
                if (fieldValue.getFieldDef().getId().getFieldId() == fieldId) {
                    if (isDouble(fieldValue.getValue())) {
                        if (isDouble(fieldValue.getValue())) {
                            double realAmount = globalVars.posToRealAmount(caseDef, fieldId);
                            String formattedAmount = String.format("%.2f", realAmount);
                            logger.info("amount   " + formattedAmount);
                            fieldValue.setValue(formattedAmount);
                        }
                    }

                }
            }
        });

        return caseDef;
    }


    public ResponseApiJson<?> addCasesDefinition(List<PosCasesDefinition> casesDefinition){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(casesDefinition.toString());
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            String caseNo = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
            for (PosCasesDefinition element : casesDefinition) {
                logger.info("\n*** case bank code *** " + element.getId().getBankCode());
                Optional<PosCasesDefinition> casesDefinitiontoCheck = casesRepository.findById(element.getId());
                if (casesDefinitiontoCheck.isEmpty()) {
                    element.getId().setCaseNo(caseNo);
                    element.setFields(element.getFields().stream().map(field -> {
                        logger.info("\n*** field bank code *** " + field.getFieldDef().getId().getBankCode());
                        // TODO : add optional value check
                        PosFieldsDefinition f = fieldDefinitionRepo.findById(field.getFieldDef().getId()).get();
                        PosFieldValue fieldValue = new PosFieldValue();
                        fieldValue.setCaseDef(element);
                        fieldValue.setFieldDef(f);
                        fieldValue.setValue(field.getValue());
                        IntStream.rangeClosed(4, 6).filter(fieldId -> fieldValue.getFieldDef().getId().getFieldId() == fieldId).filter(fieldId -> isDouble(fieldValue.getValue())).forEach(fieldId -> fieldValue.setValue(globalVars.realToPosAmount(element, fieldId, Double.parseDouble(fieldValue.getValue()))));
                        // logger.info(fieldValue.getCaseDef().toString());
                        return fieldValue;
                    }).collect(Collectors.toList()));
                    // logger.info(element.toString());
                    // logger.info(element.getFields().toString());
                    casesRepository.save(element);

                    // Track user activity
                    UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "casesDefintion", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert casesDefintion", "casesDefintion inserted successfully", new Date());
                    userActivityTrackingRepository.save(userTrace);


                } else {
                    // Track user activity
                    UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "casesDefintion", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Insert casesDefintion", "this casesDefintion already exist !", new Date());
                    userActivityTrackingRepository.save(userTrace);
                    return new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CasesDefinition already exist !");
                }
            }
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted casesDefinition successfully !");
        } catch (Exception e ){
            logger.info("Exception of getOneCasesDefinition "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCasesDefinition please check with your provider !");
        }
    }

    public ResponseApiJson<?> updateCasesDefinition(List<PosCasesDefinition> casesDefinitionList) {
        String idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            for (PosCasesDefinition casesDefinition : casesDefinitionList) {
                Optional<PosCasesDefinition> casesDefinitionto = casesRepository.findById(casesDefinition.getId());
                if (casesDefinitionto.isPresent()) {
                    casesDefinition.setFields(casesDefinition.getFields().stream().map(field -> {
//                    logger.info("\n*** field bank code *** " + field.getFieldDef().getId().getBankCode());
                        // TODO : add optional value check
                        PosFieldsDefinition f = fieldDefinitionRepo.findById(field.getFieldDef().getId()).get();
                        PosFieldValue fieldValue = new PosFieldValue();
                        fieldValue.setCaseDef(casesDefinition);
                        fieldValue.setFieldDef(f);
                        fieldValue.setValue(field.getValue());
                        IntStream.rangeClosed(4, 6).filter(fieldId -> fieldValue.getFieldDef().getId().getFieldId() == fieldId).filter(fieldId -> isDouble(fieldValue.getValue())).forEach(fieldId -> fieldValue.setValue(globalVars.realToPosAmount(casesDefinition, fieldId, Double.parseDouble(fieldValue.getValue()))));
                        // logger.info(fieldValue.getCaseDef().toString());
                        return fieldValue;
                    }).collect(Collectors.toList()));
//                logger.info("###################################");
//                logger.info(casesDefinition.toString());
//                logger.info("###################################");
//                logger.info(casesDefinition.getFields());
//                logger.info("###################################");
                    casesRepository.save(casesDefinition);

                    // Track user activity
                    UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "casesDefintion", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Update casesDefintion", "casesDefintion updated successfully", new Date());
                    userActivityTrackingRepository.save(userTrace);

                } else {
                    // Track user activity
                    UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "casesDefintion", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Update casesDefintion", "this casesDefintion  does not exit !", new Date());
                    userActivityTrackingRepository.save(userTrace);


                    return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this casesDefinition does not exit !");
                }
            }
            return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update casesDefinition successfully !");
        } catch (Exception e) {
            logger.info("Exception of getOneCasesDefinition "  + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Update", "casesDefinition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Update casesDefinition", " Some issues in updateCasesService please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCasesService please check with your provider !");
        }
    }

    public ResponseApiJson<String> deleteCasesDefinition(PosCasesDefinitionId id) {
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<PosCasesDefinition> casesDefinition = casesRepository.findById_CaseNoAndId_BankCodeAndId_CaseProtocole(
                    id.getCaseNo(),
                    id.getBankCode(),
                    id.getCaseProtocole()
            );
            if (!casesDefinition.isEmpty()) {
                casesRepository.deleteAll(casesDefinition);

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Delete", "casesDefinition", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Delete casesDefinition", "casesDefinition deleted successfully", new Date());
                userActivityTrackingRepository.save(userTrace);


                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete casesDefinition successfully !");
            } else {

                // Track user activity
                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Delete", "casesDefinition", "000.000.00.00", "Failed", GlobalVars.NOTEXIST, "Delete casesDefinition", "CasesDefinition does not exist !", new Date());
                userActivityTrackingRepository.save(userTrace);


                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST , "CasesDefinition does not exist !");
            }
        } catch (Exception e) {
            logger.info("Delete casesDefinition Exception: " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "casesDefinition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete casesDefinition", " Some issues in deleteCasesDefinition please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCasesDefinition terminated with issue");
        }
    }


}
