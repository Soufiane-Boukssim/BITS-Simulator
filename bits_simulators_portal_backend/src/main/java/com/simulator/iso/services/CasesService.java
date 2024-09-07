package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.dtos.CasesDefDTO;
import com.simulator.dtos.CasesDefinitionDTO;
import com.simulator.entities.CasesDefinition;
import com.simulator.entities.CasesDefinitionId;
import com.simulator.entities.FieldValue;
import com.simulator.entities.FieldsDefinition;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CasesRepository;
import com.simulator.repository.FieldsDefinitionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.simulator.config.GlobalVars.isDouble;


@Service
public  class CasesService {
    private final Logger logger = LogManager.getLogger(CasesService.class);
    private final CasesRepository casesRepository;
    private final FieldsDefinitionRepository fieldDefinitionRepo;
    private final SubFieldParsing subFieldParsing;

    @Autowired
    GlobalVars globalVars;
    @Autowired
    public CasesService(CasesRepository casesRepository, FieldsDefinitionRepository fieldDefinitionRepo, SubFieldParsing subFieldParsing) {
        this.casesRepository = casesRepository;
        this.fieldDefinitionRepo = fieldDefinitionRepo;
        this.subFieldParsing = subFieldParsing;
    }

    public ResponseApiJson<List<CasesDefDTO>> getAllCasesDefinitions(CasesDefinitionId casesDefinitionId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<Object[]> queryResult = casesRepository.findByBankCodeAndCaseProtocole(casesDefinitionId.getBankCode(), casesDefinitionId.getCaseProtocole());
//            for (Object[] a : queryResult) {
//                logger.info(Arrays.toString(a));
//            }
            List<CasesDefDTO> casesDefList = queryResult.stream()
                    .map(result -> new CasesDefDTO(
                            (String) result[0],       // Assuming caseNo is a String
                            (String) result[3],       // Assuming caseDesc is a String
                            (String) result[1],       // Assuming bankCode is a String
                            (String) result[2]     // Assuming caseProtocole is a Character
                    ))
                    .collect(Collectors.toList());
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Success!", casesDefList);
        } catch (Exception e) {
            logger.error("Exception in getAllCasesDefinitions : " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Exception when getting casesDefintion records");
        }
    }

    public ResponseApiJson<Map<String, CasesDefinitionDTO>> getCaseDefinition(CasesDefinitionId casesDefinitionId) {
        String idRequest = "GC_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<CasesDefinition> cases = casesRepository.findById_CaseNoAndId_BankCodeAndId_CaseProtocole(
                    casesDefinitionId.getCaseNo(),
                    casesDefinitionId.getBankCode(),
                    casesDefinitionId.getCaseProtocole()
            );
            // Convert ISO amounts to real amounts for fields 4, 5, and 6
            List<CasesDefinition> lastCases = cases.stream()
                    .map(this::convertFieldAmount)
                    .toList();
            List<CasesDefinitionDTO> casesDefs = subFieldParsing.processFields(cases);

            logger.info(casesDefs.size());
            Map<String, CasesDefinitionDTO> casesMap = casesDefs.stream()
                    .filter(caseDef -> 'I' == caseDef.id().getCaseType() || 'O' == caseDef.id().getCaseType())
                    .collect(Collectors.toMap(
                            caseDef -> 'I' == caseDef.id().getCaseType() ? "in" : "out",
                            caseDef -> caseDef
                    ));
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Success!", casesMap);
        } catch (Exception e) {
            logger.error("Exception in getAllCasesDefinitions : " + e.getMessage());
            e.printStackTrace();
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Exception when getting casesDefintion records");
        }
    }

    private CasesDefinition convertFieldAmount(CasesDefinition caseDef) {
        caseDef.getFields().forEach(fieldValue -> {
            for (int fieldId = 4; fieldId <= 6; fieldId++) {
                if (fieldValue.getFieldDef().getId().getFieldId() == fieldId) {
                    if (isDouble(fieldValue.getValue())) {
                        if (isDouble(fieldValue.getValue())) {
                            double realAmount = globalVars.isoToRealAmount(caseDef, fieldId);
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

    @CacheEvict(value = "caseSetInfos", allEntries = true)
    public ResponseApiJson<?> addCasesDefinition(List<CasesDefinition> casesDefinition){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(casesDefinition.toString());
        try {
            String caseNo = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
            for (CasesDefinition element : casesDefinition) {
                logger.info("\n*** case bank code *** " + element.getId().getBankCode());
                Optional<CasesDefinition> casesDefinitiontoCheck = casesRepository.findById(element.getId());
                if (casesDefinitiontoCheck.isEmpty()) {
                    element.getId().setCaseNo(caseNo);
                    element.setFields(element.getFields().stream().map(field -> {
                        logger.info("\n*** field bank code *** " + field.getFieldDef().getId().getBankCode());
                        // TODO : add optional value check
                        FieldsDefinition f = fieldDefinitionRepo.findById(field.getFieldDef().getId()).get();
                        FieldValue fieldValue = new FieldValue();
                        fieldValue.setCaseDef(element);
                        fieldValue.setFieldDef(f);
                        fieldValue.setValue(field.getValue());
                        IntStream.rangeClosed(4, 6).filter(fieldId -> fieldValue.getFieldDef().getId().getFieldId() == fieldId).filter(fieldId -> isDouble(fieldValue.getValue())).forEach(fieldId -> fieldValue.setValue(globalVars.realToIsoAmount(element, fieldId, Double.parseDouble(fieldValue.getValue()))));
                        // logger.info(fieldValue.getCaseDef().toString());
                        return fieldValue;
                    }).collect(Collectors.toList()));
                    // logger.info(element.toString());
                    // logger.info(element.getFields().toString());
                    casesRepository.save(element);
                } else {
                    return new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CasesDefinition already exist !");
                }
            }
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted casesDefinition successfully !");
        } catch (Exception e ){
            logger.info("Exception of getOneCasesDefinition "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCasesDefinition please check with your provider !");
        }
    }


    @CacheEvict(value = "caseSetInfos", allEntries = true)
    public ResponseApiJson<?> updateCasesDefinition(List<CasesDefinition> casesDefinitionList) {
        String idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try {
            for (CasesDefinition casesDefinition : casesDefinitionList) {
                Optional<CasesDefinition> casesDefinitionto = casesRepository.findById(casesDefinition.getId());
                if (casesDefinitionto.isPresent()) {
                    casesDefinition.setFields(casesDefinition.getFields().stream().map(field -> {
//                    logger.info("\n*** field bank code *** " + field.getFieldDef().getId().getBankCode());
                        // TODO : add optional value check
                        FieldsDefinition f = fieldDefinitionRepo.findById(field.getFieldDef().getId()).get();
                        FieldValue fieldValue = new FieldValue();
                        fieldValue.setCaseDef(casesDefinition);
                        fieldValue.setFieldDef(f);
                        fieldValue.setValue(field.getValue());
                        IntStream.rangeClosed(4, 6).filter(fieldId -> fieldValue.getFieldDef().getId().getFieldId() == fieldId).filter(fieldId -> isDouble(fieldValue.getValue())).forEach(fieldId -> fieldValue.setValue(globalVars.realToIsoAmount(casesDefinition, fieldId, Double.parseDouble(fieldValue.getValue()))));
                        // logger.info(fieldValue.getCaseDef().toString());
                        return fieldValue;
                    }).collect(Collectors.toList()));
//                logger.info("###################################");
//                logger.info(casesDefinition.toString());
//                logger.info("###################################");
//                logger.info(casesDefinition.getFields());
//                logger.info("###################################");
                    casesRepository.save(casesDefinition);
                } else {
                    return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this casesDefinition does not exit !");
                }
            }
            return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update casesDefinition successfully !");
        } catch (Exception e) {
            logger.info("Exception of getOneCasesDefinition "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCasesService please check with your provider !");
        }
    }

    public ResponseApiJson<String> deleteCasesDefinition(CasesDefinitionId id) {
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<CasesDefinition> casesDefinition = casesRepository.findById_CaseNoAndId_BankCodeAndId_CaseProtocole(
                    id.getCaseNo(),
                    id.getBankCode(),
                    id.getCaseProtocole()
            );
            if (!casesDefinition.isEmpty()) {
                casesRepository.deleteAll(casesDefinition);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete casesDefinition successfully !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST , "CasesDefinition does not exist !");
            }
        } catch (Exception e) {
            logger.info("Delete casesDefinition Exception: " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCasesDefinition terminated with issue");
        }
    }


}
