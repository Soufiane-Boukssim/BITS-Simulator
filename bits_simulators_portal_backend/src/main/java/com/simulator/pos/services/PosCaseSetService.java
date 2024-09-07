package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.dtos.pos.PosCaseSetInfoDTO;
import com.simulator.entities.pos.*;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.pos.PosCaseSetRepository;
import com.simulator.repository.pos.PosCasesRepository;
import com.simulator.repository.pos.PosFieldsDefinitionRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.simulator.config.GlobalVars.isDouble;


@Service
public class PosCaseSetService {
    private final Logger logger = LogManager.getLogger(PosCaseSetService.class);
    @Autowired
    PosCaseSetRepository caseSetRepository;

    @Autowired
    PosCasesRepository casesRepository;

    @Autowired
    GlobalVars globalVars;

    @Autowired
    PosFieldsDefinitionRepository fieldDefinitionRepo;
    public ResponseApiJson<List<PosCaseSetInfo>> getAllCaseSetInfos(PosCasesSetInfoId casesSetInfoId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        logger.info("-->PosCasesSetInfoId ::"+casesSetInfoId);
        try {
            List<PosCaseSetInfo> caseSetInfos = caseSetRepository.findByBankCodeAndCaseSetProtocole(casesSetInfoId.getBankCode(), casesSetInfoId.getCaseSetProtocole());

            for (PosCaseSetInfo caseSetInfo : caseSetInfos) {
                // Iterate over each PosCasesDefinition in PosCaseSetInfo
                for (PosCasesDefinition casesDefinition : caseSetInfo.getPosCasesDefinitions()) {
                    // Apply your modifications to each PosCasesDefinition
                    modifyCasesDefinition(casesDefinition);
                }
            }

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all PosCaseSetInfo succesfully  !", caseSetInfos);
        } catch (Exception e) {
            logger.info("Exception of this::getAllCaseSetInfos " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCaseSetInfos please check with your provider !");
        }
    }
    private void modifyCasesDefinition(PosCasesDefinition casesDefinition) {
        // Implement your modification logic here
        casesDefinition.setFields(casesDefinition.getFields().stream().map(field -> {
         //   logger.info("\n*** field bank code *** " + field.getFieldDef().getId().getBankCode());
            // TODO : add optional value check
            PosFieldsDefinition f = fieldDefinitionRepo.findById(field.getFieldDef().getId()).get();
            PosFieldValue fieldValue = new PosFieldValue();
            fieldValue.setCaseDef(casesDefinition);
            fieldValue.setFieldDef(f);
            fieldValue.setValue(field.getValue());
            for (int fieldId = 4; fieldId <= 6; fieldId++) {
                if (fieldValue.getFieldDef().getId().getFieldId() == fieldId) {
                    if (isDouble(fieldValue.getValue())) {
                        double realAmount = globalVars.posToRealAmount(casesDefinition, fieldId);
                        String formattedAmount = String.format("%.2f", realAmount);
                        logger.info("amount   " + formattedAmount);
                        fieldValue.setValue(formattedAmount);
                    }
                }
            }
            // logger.info(fieldValue.getCaseDef().toString());
            return fieldValue;
        }).collect(Collectors.toList()));
    }
    public ResponseApiJson<PosCaseSetInfoDTO> getOneCaseSetInfo(PosCasesSetInfoId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosCaseSetInfo> caseSetInfo = caseSetRepository.findById(id);
            if (caseSetInfo.isPresent()) {
                PosCaseSetInfoDTO caseSetDto = new PosCaseSetInfoDTO(
                        caseSetInfo.get().getId(),
                        caseSetInfo.get().getCaseSetDesc(),
                        getDistinctCasesIDs(caseSetInfo.get().getPosCasesDefinitions().stream().map(
                                PosCasesDefinition::getId
                        ).toList())
                );
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get one caseSetInfo successfully !", caseSetDto);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "PosCaseSetInfo does not exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of  getOneCaseSetInfo " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " some issues in getOneCaseSetInfo please check with your provider !");
        }
    }

    public ResponseApiJson<?> addCaseSetInfo(PosCaseSetInfoDTO caseSetInfoDTO) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(caseSetInfoDTO.toString());
        try {
            Optional<PosCaseSetInfo> caseSetInfotoCheck = caseSetRepository.findById(caseSetInfoDTO.id());
            if (caseSetInfotoCheck.isEmpty()) {
                String id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
                List<PosCasesDefinition> cases = casesRepository.findAllById_CaseNoAndId_BankCodeAndId_CaseProtocoleIn(
                        caseSetInfoDTO.cases().stream().map(
                                PosCasesDefinitionId::getCaseNo
                        ).toList(),
                        caseSetInfoDTO.cases().stream().map(
                                PosCasesDefinitionId::getBankCode
                        ).toList(),
                        caseSetInfoDTO.cases().stream().map(
                                PosCasesDefinitionId::getCaseProtocole
                        ).toList()
                );
                logger.info("**************" + cases.size() + "**************");
                caseSetInfoDTO.id().setCaseSetId(id);
                PosCaseSetInfo caseSetInfo = new PosCaseSetInfo(
                        caseSetInfoDTO.id(), caseSetInfoDTO.caseSetDesc(), cases
                );
                caseSetRepository.save(caseSetInfo);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted caseSetInfo successfully  !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this PosCaseSetInfo already exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneCaseSetInfo " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addCaseSetInfo please check with your provider !");
        }
    }

    public ResponseApiJson<?> updateCaseSetInfo(PosCaseSetInfoDTO caseSetInfoDTO) {
        String idRequest = "UMS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosCaseSetInfo> caseSetInfoCheck = caseSetRepository.findById(caseSetInfoDTO.id());
            if (caseSetInfoCheck.isPresent()) {
                List<PosCasesDefinition> cases = casesRepository.findAllById_CaseNoAndId_BankCodeAndId_CaseProtocoleIn(
                        caseSetInfoDTO.cases().stream().map(
                                PosCasesDefinitionId::getCaseNo
                        ).toList(),
                        caseSetInfoDTO.cases().stream().map(
                                PosCasesDefinitionId::getBankCode
                        ).toList(),
                        caseSetInfoDTO.cases().stream().map(
                                PosCasesDefinitionId::getCaseProtocole
                        ).toList()
                );
                PosCaseSetInfo caseSetInfo = new PosCaseSetInfo(
                        caseSetInfoDTO.id(), caseSetInfoDTO.caseSetDesc(), cases
                );
                caseSetRepository.save(caseSetInfo);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update  caseSetInfo  sucessfuly !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "this caseSetInfo  does not exit !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneCaseSetInfo " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateCaseSetService please check with your provider !");
        }
    }

    public ResponseApiJson<?> deleteCaseSetInfo(PosCasesSetInfoId id) {
        String idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosCaseSetInfo> caseSetInfo = caseSetRepository.findById(id);
            caseSetInfo.ifPresent(caseSetRepository::delete);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete caseSetInfo succesfully !");
        } catch (Exception e) {
            logger.info("Delete caseSetInfo Exception: " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteCaseSetInfo terminated with issue");
        }
    }

    public List<PosCasesDefinitionId> getDistinctCasesIDs(List<PosCasesDefinitionId> cases) {
        Map<String, List<PosCasesDefinitionId>> groupedCases = cases.stream()
                .collect(Collectors.groupingBy(PosCasesDefinitionId::getCaseNo));

        return groupedCases.values().stream()
                .map(caseGroup -> {
                    PosCasesDefinitionId firstCase = caseGroup.get(0);
                    firstCase.setCaseType(null);
                    return firstCase;
                })
                .toList();
    }
}





