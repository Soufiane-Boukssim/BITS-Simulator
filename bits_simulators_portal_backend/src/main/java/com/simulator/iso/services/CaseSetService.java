package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.dtos.CaseSetInfoDTO;
import com.simulator.entities.*;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CaseSetRepository;
import com.simulator.repository.CasesRepository;
import com.simulator.repository.FieldsDefinitionRepository;
import com.simulator.repository.UserActivityTrackingRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.simulator.config.GlobalVars.isDouble;


@Service
public class CaseSetService {
    private final Logger logger = LogManager.getLogger(CaseSetService.class);
    @Autowired
    CaseSetRepository caseSetRepository;

    @Autowired
    CasesRepository casesRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;


    @Autowired
    GlobalVars globalVars;

    @Autowired
    FieldsDefinitionRepository fieldDefinitionRepo;

    @Cacheable(value = "caseSetInfos", key = "#casesSetInfoId")
    public ResponseApiJson<List<CaseSetInfo>> getAllCaseSetInfos(CasesSetInfoId casesSetInfoId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
//        logger.info("getAllCaseSetInfos ::> "+casesSetInfoId);
        try {
            List<CaseSetInfo> caseSetInfos = caseSetRepository.findByBankCodeAndCaseSetProtocole(casesSetInfoId.getBankCode(), casesSetInfoId.getCaseSetProtocole());

            for (CaseSetInfo caseSetInfo : caseSetInfos) {
                // Iterate over each CasesDefinition in CaseSetInfo
                for (CasesDefinition casesDefinition : caseSetInfo.getCasesDefinitions()) {
                    // Apply your modifications to each CasesDefinition
                    modifyCasesDefinition(casesDefinition);
                }
            }
             //logger.info("test :::>");
            // Track user activity
            //UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CaseSetInfo", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve CaseSetInfo", "CaseSetInfo retrieved successfully", new Date());
            //userActivityTrackingRepository.save(userTrace);
            //logger.info("test :::>");


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all CaseSetInfo succesfully  !", caseSetInfos);
        } catch (Exception e) {
            logger.info("Exception of this::getAllCaseSetInfos " + e.getMessage());

            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "CaseSetInfo", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve CaseSetInfo", "some issues in getAllCaseSetInfos please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCaseSetInfos please check with your provider !");
        }
    }
    private void modifyCasesDefinition(CasesDefinition casesDefinition) {
        // Implement your modification logic here
        casesDefinition.setFields(casesDefinition.getFields().stream().map(field -> {
            //logger.info("\n*** field bank code *** " + field.getFieldDef().getId().getBankCode());
            // TODO : add optional value check
            FieldsDefinition f = fieldDefinitionRepo.findById(field.getFieldDef().getId()).get();
            FieldValue fieldValue = new FieldValue();
            fieldValue.setCaseDef(casesDefinition);
            fieldValue.setFieldDef(f);
            fieldValue.setValue(field.getValue());
            for (int fieldId = 4; fieldId <= 6; fieldId++) {
                if (fieldValue.getFieldDef().getId().getFieldId() == fieldId) {
                    if (isDouble(fieldValue.getValue())) {
                        double realAmount = globalVars.isoToRealAmount(casesDefinition, fieldId);
                        String formattedAmount = String.format("%.2f", realAmount);
//                        logger.info("amount   " + formattedAmount);
                        fieldValue.setValue(formattedAmount);
                    }
                }
            }
            // logger.info(fieldValue.getCaseDef().toString());
            return fieldValue;
        }).collect(Collectors.toList()));
    }
    public ResponseApiJson<CaseSetInfoDTO> getOneCaseSetInfo(CasesSetInfoId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CaseSetInfo> caseSetInfo = caseSetRepository.findById(id);
            if (caseSetInfo.isPresent()) {
                CaseSetInfoDTO caseSetDto = new CaseSetInfoDTO(
                        caseSetInfo.get().getId(),
                        caseSetInfo.get().getCaseSetDesc(),
                        getDistinctCasesIDs(caseSetInfo.get().getCasesDefinitions().stream().map(
                                CasesDefinition::getId
                        ).toList())
                );
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get one caseSetInfo successfully !", caseSetDto);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "CaseSetInfo does not exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of  getOneCaseSetInfo " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " some issues in getOneCaseSetInfo please check with your provider !");
        }
    }

    @CacheEvict(value = "caseSetInfos", allEntries = true)
    public ResponseApiJson<?> addCaseSetInfo(CaseSetInfoDTO caseSetInfoDTO) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(caseSetInfoDTO.toString());
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        logger.info("caseSetInfoDTO ::>"+caseSetInfoDTO);
        try {
            Optional<CaseSetInfo> caseSetInfotoCheck = caseSetRepository.findById(caseSetInfoDTO.id());
            if (caseSetInfotoCheck.isEmpty()) {
                String id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
                List<CasesDefinition> cases = casesRepository.findAllById_CaseNoAndId_BankCodeAndId_CaseProtocoleIn(
                        caseSetInfoDTO.cases().stream().map(
                                CasesDefinitionId::getCaseNo
                        ).toList(),
                        caseSetInfoDTO.cases().stream().map(
                                CasesDefinitionId::getBankCode
                        ).toList(),
                        caseSetInfoDTO.cases().stream().map(
                                CasesDefinitionId::getCaseProtocole
                        ).toList()
                );
                logger.info("**************" + cases + "**************");
                caseSetInfoDTO.id().setCaseSetId(id);
                CaseSetInfo caseSetInfo = new CaseSetInfo(
                        caseSetInfoDTO.id(), caseSetInfoDTO.caseSetDesc(), cases
                );
                logger.info("**************" + caseSetInfo+ "**************");
                caseSetRepository.save(caseSetInfo);

                // Track user activity
                //UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "caseSetInfo", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert caseSetInfo", "caseSetInfo inserted successfully", new Date());
               // userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted caseSetInfo successfully  !");
            } else {
                // Track user activity
              //  UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "CaseSetInfo", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Insert commsParam", "this CaseSetInfo already exist !", new Date());
                //userActivityTrackingRepository.save(userTrace);


                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this CaseSetInfo already exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneCaseSetInfo " + e.getMessage());
            // Track user activity for exception
            //UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "CaseSetInfo", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert CaseSetInfo", " Some issues in addCaseSetInfo please check with your provider !", new Date());
            //userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addCaseSetInfo please check with your provider !");
        }
    }

    @CacheEvict(value = "caseSetInfos", allEntries = true)
    public ResponseApiJson<?> updateCaseSetInfo(CaseSetInfoDTO caseSetInfoDTO) {
        String idRequest = "UMS_" + new Date().getTime() + (Math.random() * 9999);

        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            Optional<CaseSetInfo> caseSetInfoCheck = caseSetRepository.findById(caseSetInfoDTO.id());
            if (caseSetInfoCheck.isPresent()) {
                List<CasesDefinition> cases = casesRepository.findAllById_CaseNoAndId_BankCodeAndId_CaseProtocoleIn(
                        caseSetInfoDTO.cases().stream().map(
                                CasesDefinitionId::getCaseNo
                        ).toList(),
                        caseSetInfoDTO.cases().stream().map(
                                CasesDefinitionId::getBankCode
                        ).toList(),
                        caseSetInfoDTO.cases().stream().map(
                                CasesDefinitionId::getCaseProtocole
                        ).toList()
                );
                CaseSetInfo caseSetInfo = new CaseSetInfo(
                        caseSetInfoDTO.id(), caseSetInfoDTO.caseSetDesc(), cases
                );
                caseSetRepository.save(caseSetInfo);
                // Track user activity
                //UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "caseSetInfo", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Update caseSetInfo", "caseSetInfo updated successfully", new Date());
               // userActivityTrackingRepository.save(userTrace);


                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update  caseSetInfo  sucessfuly !");
            } else {

                // Track user activity
               // UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "caseSetInfo", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Update caseSetInfo", "this caseSetInfo  does not exit !", new Date());
                //userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "this caseSetInfo  does not exit !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneCaseSetInfo " + e.getMessage());

            // Track user activity for exception
            //UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Update", "caseSetInfo", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Update caseSetInfo", " Some issues in updateCaseSetService please check with your provider !", new Date());
            //userActivityTrackingRepository.save(userTrace);


            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateCaseSetService please check with your provider !");
        }
    }

    public ResponseApiJson<?> deleteCaseSetInfo(CasesSetInfoId id) {
        String idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            Optional<CaseSetInfo> caseSetInfo = caseSetRepository.findById(id);
            caseSetInfo.ifPresent(caseSetRepository::delete);
            // Track user activity
            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Delete", "caseSetInfo", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Delete caseSetInfo", "caseSetInfo deleted successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete caseSetInfo succesfully !");
        } catch (Exception e) {
            logger.info("Delete caseSetInfo Exception: " + e.getMessage());
            // Track user activity for exception
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "caseSetInfo", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete caseSetInfo", " Some issues in deleteCaseSetInfo please check with your provider !", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteCaseSetInfo terminated with issue");
        }
    }

    public List<CasesDefinitionId> getDistinctCasesIDs(List<CasesDefinitionId> cases) {
        Map<String, List<CasesDefinitionId>> groupedCases = cases.stream()
                .collect(Collectors.groupingBy(CasesDefinitionId::getCaseNo));

        return groupedCases.values().stream()
                .map(caseGroup -> {
                    CasesDefinitionId firstCase = caseGroup.get(0);
                    firstCase.setCaseType(null);
                    return firstCase;
                })
                .toList();
    }
}





