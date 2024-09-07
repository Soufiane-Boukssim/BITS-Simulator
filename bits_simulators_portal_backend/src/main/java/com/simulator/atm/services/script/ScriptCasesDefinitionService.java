package com.simulator.atm.services.script;

import com.simulator.atm.dto.ScriptCasesDefinitionDto;
import com.simulator.atm.model.AtmCardProfil;
import com.simulator.atm.model.FieldCases;
import com.simulator.atm.model.ScriptCasesDefinition;
import com.simulator.atm.repositories.reposcript.AtmCardProfilRepository;
import com.simulator.atm.repositories.reposcript.ScriptCasesDefinitionRepository;
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
public class ScriptCasesDefinitionService {

    private final Logger logger = LogManager.getLogger(ScriptCasesDefinitionService.class);

    @Autowired
    private ScriptCasesDefinitionRepository scriptCasesDefinitionRepository;

    @Autowired
    private FieldCasesService fieldsService;

    @Autowired
    private AtmCardProfilRepository cardProfileRepository;

    @Autowired
    private ModelMapper modelMapper;
    public ResponseApiJson<ScriptCasesDefinitionDto> createScriptCasesDefinition(ScriptCasesDefinitionDto scriptCasesDefinitionDto) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            AtmCardProfil cardProfile = cardProfileRepository.findFirstByCardNo(scriptCasesDefinitionDto.getIdCardProfil().getCardNo());
            if (cardProfile == null) {
                throw new RuntimeException("CardProfile with this issuerCode does not exist");
            }

            ScriptCasesDefinition scriptCasesDefinition = modelMapper.map(scriptCasesDefinitionDto, ScriptCasesDefinition.class);

            scriptCasesDefinition.setCardProfile(cardProfile);
            scriptCasesDefinition.setFieldCases(scriptCasesDefinitionDto.getFieldCases().stream()
                    .map(fieldCasesDto -> fieldsService.createFieldCases(fieldCasesDto).getResult())
                    .collect(Collectors.toSet()));
            System.out.println("scriptCasesDefinition = " + scriptCasesDefinition.getFieldCases().size());
            scriptCasesDefinitionRepository.save(scriptCasesDefinition);

            ScriptCasesDefinitionDto savedScriptCasesDefinitionDto = modelMapper.map(scriptCasesDefinition, ScriptCasesDefinitionDto.class);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted ScriptCasesDefinition successfully  !", savedScriptCasesDefinitionDto);
        } catch (Exception e) {
           // logger.info("Exception of createScriptCasesDefinition " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in createScriptCasesDefinition please check with your provider !");
        }
    }

    public void deleteScriptCasesDefinition(Long id) {
        ScriptCasesDefinition scriptCasesDefinition = scriptCasesDefinitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ScriptCasesDefinition not found"));
        scriptCasesDefinitionRepository.delete(scriptCasesDefinition);
    }

    public ResponseApiJson<ScriptCasesDefinitionDto> getScriptCasesDefinitionById(Long id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            ScriptCasesDefinition scriptCasesDefinition = scriptCasesDefinitionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ScriptCasesDefinition not found"));
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get ScriptCasesDefinition successfully  !", modelMapper.map(scriptCasesDefinition, ScriptCasesDefinitionDto.class));
        } catch (Exception e) {
           // logger.info("Exception of getScriptCasesDefinitionById " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getScriptCasesDefinitionById please check with your provider !");
        }
    }

    public ResponseApiJson<ScriptCasesDefinitionDto> updateScriptCasesDefinition(Long id, ScriptCasesDefinitionDto scriptCasesDefinitionDto) {
        String idRequest = "UPDATE_SCRIPT_CASE_" + new Date().getTime() + (Math.random() * 9999);
        try {
            ScriptCasesDefinition scriptCasesDefinition = scriptCasesDefinitionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ScriptCasesDefinition not found"));

            scriptCasesDefinition.setLibelle(scriptCasesDefinitionDto.getLibelle());
            scriptCasesDefinition.setTypeScript(scriptCasesDefinitionDto.getTypeScript());
            scriptCasesDefinition.setCardProfile(cardProfileRepository.findFirstByCardNo(scriptCasesDefinitionDto.getIdCardProfil().getCardNo()));
            scriptCasesDefinition.setFieldCases(scriptCasesDefinitionDto.getFieldCases().stream()
                    .map(fieldCasesDto -> modelMapper.map(fieldCasesDto, FieldCases.class))
                    .collect(Collectors.toSet()));
            ScriptCasesDefinition updatedScriptCasesDefinition = scriptCasesDefinitionRepository.save(scriptCasesDefinition);
            updatedScriptCasesDefinition.setIdCasesDefinition(id);
            ScriptCasesDefinitionDto updatedScriptCasesDefinitionDto = modelMapper.map(updatedScriptCasesDefinition, ScriptCasesDefinitionDto.class);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Updated ScriptCasesDefinition successfully!", updatedScriptCasesDefinitionDto);
        } catch (Exception e) {
           // logger.info("Exception of updateScriptCasesDefinition " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateScriptCasesDefinition, please check with your provider !");
        }
    }

    public ResponseApiJson<List<ScriptCasesDefinitionDto>> getAllScriptCasesDefinitions(String bankCode) {
        String idRequest = "CPS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<ScriptCasesDefinition> scriptCasesDefinitions = scriptCasesDefinitionRepository.findByBankCode(bankCode);
            List<ScriptCasesDefinitionDto> scriptCasesDefinitionDtos = scriptCasesDefinitions.stream()
                    .map(scriptCasesDefinition -> modelMapper.map(scriptCasesDefinition, ScriptCasesDefinitionDto.class))
                    .collect(Collectors.toList());

           // logger.info("scriptCasesDefinitions =>",scriptCasesDefinitions);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all ScriptCasesDefinitions successfully  !", scriptCasesDefinitionDtos);
        } catch (Exception e) {
           // logger.info("Exception of getAllScriptCasesDefinitions " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllScriptCasesDefinitions please check with your provider !");
        }
    }
}
