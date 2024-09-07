package com.simulator.atm.services.script;

import com.simulator.atm.dto.FieldCasesDto;
import com.simulator.atm.model.ATMfield;
import com.simulator.atm.model.FieldCases;
import com.simulator.atm.repositories.reposcript.ATMfieldRepository;
import com.simulator.atm.repositories.reposcript.FieldCasesRepository;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FieldCasesService {

    private final Logger logger = LogManager.getLogger(FieldCasesService.class);

    @Autowired
    private FieldCasesRepository fieldCasesRepository;

    @Autowired
    private ATMfieldRepository atmfieldRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseApiJson<FieldCases> createFieldCases(FieldCasesDto fieldCasesDto) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<ATMfield> atmFields = fieldCasesDto.getAtmField().stream()
                    .map(ATMfield::getDescription)
                    .map(atmfieldRepository::findFirstByDescription)
                    .collect(Collectors.toList());

            if (atmFields.contains(null)) {
                throw new RuntimeException("One or more ATMfields with the provided descriptions do not exist");
            }

            FieldCases fieldCases = modelMapper.map(fieldCasesDto, FieldCases.class);
            fieldCases.setAtmField(atmFields);

            switch (fieldCasesDto.getPopularList()) {
                case Literal_Value:
                    fieldCases.setValue(fieldCasesDto.getValue());
                    break;
                case GENERATED_BY_SYSTEM:
                case FROM_CARD_PROFILE:
                    fieldCases.setValue(fieldCasesDto.getPopularList().name());
                    break;
            }

            fieldCasesRepository.save(fieldCases);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted FieldCases successfully  !", fieldCases);
        } catch (Exception e) {
            logger.info("Exception of createFieldCases " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in createFieldCases please check with your provider !");
        }
    }


    public ResponseApiJson<FieldCasesDto> updateFieldCases(Long id, FieldCasesDto fieldCasesDto) {
        String idRequest = "UMS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            FieldCases fieldCases = fieldCasesRepository.findById(id).orElseThrow(() -> new RuntimeException("FieldCases not found"));

            List<ATMfield> atmFields = fieldCasesDto.getAtmField().stream()
                    .map(ATMfield::getDescription)
                    .map(atmfieldRepository::findFirstByDescription)
                    .collect(Collectors.toList());

            if (atmFields.contains(null)) {
                throw new RuntimeException("One or more ATMfields with the provided descriptions do not exist");
            }

            fieldCases.setAtmField(atmFields);

            modelMapper.map(fieldCasesDto, fieldCases);

            switch (fieldCasesDto.getPopularList()) {
                case Literal_Value:
                    fieldCases.setValue(fieldCasesDto.getValue());
                    break;
                case GENERATED_BY_SYSTEM:
                case FROM_CARD_PROFILE:
                    fieldCases.setValue(fieldCasesDto.getPopularList().name());
                    break;
            }

            fieldCasesRepository.save(fieldCases);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update FieldCases successfully  !", modelMapper.map(fieldCases, FieldCasesDto.class));
        } catch (Exception e) {
            logger.info("Exception of updateFieldCases " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateFieldCases please check with your provider !");
        }
    }
    public ResponseApiJson<?> deleteFieldCases(Long id) {
        String idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            FieldCases fieldCases = fieldCasesRepository.findById(id).orElseThrow(() -> new RuntimeException("FieldCases not found"));
            fieldCasesRepository.delete(fieldCases);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete FieldCases successfully !");
        } catch (Exception e) {
            logger.info("Delete FieldCases Exception: " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteFieldCases terminated with issue");
        }
    }

    public ResponseApiJson<List<FieldCasesDto>> getAllFieldCases(String bankCode) {
        String idRequest = "CPS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<FieldCases> fieldCases = fieldCasesRepository.findByBankCode(bankCode);
            List<FieldCasesDto> fieldCasesDtos = fieldCases.stream()
                    .map(fieldCases1 -> modelMapper.map(fieldCases1, FieldCasesDto.class))
                    .collect(Collectors.toList());
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all FieldCases successfully  !", fieldCasesDtos);
        } catch (Exception e) {
            logger.info("Exception of getAllFieldCases " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllFieldCases please check with your provider !");
        }
    }
}
