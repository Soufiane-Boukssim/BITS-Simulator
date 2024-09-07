package com.simulator.atm.services.script;

import com.simulator.atm.dto.AtmCardProfilDto;
import com.simulator.atm.dto.ScriptCasesDefinitionDto;
import com.simulator.atm.dto.OperationAtmDto;
import com.simulator.atm.model.AtmCardProfil;
import com.simulator.atm.model.ScriptCasesDefinition;
import com.simulator.atm.model.OperationAtm;
import com.simulator.atm.repositories.reposcript.AtmCardProfilRepository;
import com.simulator.atm.repositories.reposcript.ScriptCasesDefinitionRepository;
import com.simulator.atm.repositories.reposcript.OperationAtmRepostory;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationAtmService {

    @Autowired
    private OperationAtmRepostory senarioScriptRepository;
    @Autowired
    private ScriptCasesDefinitionRepository scriptCasesDefinitionService;

    @Autowired
    private AtmCardProfilRepository cardProfileRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseApiJson<OperationAtmDto> addSenarioScript(OperationAtmDto senarioScriptRequestDto) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            OperationAtm operationAtm = modelMapper.map(senarioScriptRequestDto, OperationAtm.class);

//            AtmCardProfil atmCardProfil = cardProfileRepository.findFirstByCardNo(senarioScriptRequestDto.getAtmCardProfilDto().getId().getCardNo());
            String cardNo = senarioScriptRequestDto.getAtmCardProfilDto().getId().getCardNo();
            AtmCardProfil atmCardProfil = cardProfileRepository.findFirstByCardNo(cardNo);
            if (atmCardProfil == null) {
                throw new RuntimeException("AtmCardProfil with this issuerCode does not exist");
            }
            operationAtm.setCardProfile(atmCardProfil);
            List<ScriptCasesDefinition> scriptCasesDefinitions = senarioScriptRequestDto.getScriptCasesDefinitions().stream()
                    .map(scriptCasesDefinitionDto -> {
                        ScriptCasesDefinition scriptCasesDefinition = scriptCasesDefinitionService.findByLibelle(scriptCasesDefinitionDto.getLibelle());
                        return scriptCasesDefinition;
                    })
                    .collect(Collectors.toList());

            operationAtm.setScriptCasesDefinitions(scriptCasesDefinitions);
            OperationAtm savedOperationAtm = senarioScriptRepository.save(operationAtm);
            OperationAtmDto savedOperationAtmDto = modelMapper.map(savedOperationAtm, OperationAtmDto.class);

            List<ScriptCasesDefinitionDto> scriptCasesDefinitionDtos = scriptCasesDefinitions.stream()
                    .map(scriptCasesDefinition -> modelMapper.map(scriptCasesDefinition, ScriptCasesDefinitionDto.class))
                    .collect(Collectors.toList());

            savedOperationAtmDto.setScriptCasesDefinitions(scriptCasesDefinitionDtos);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted SenarioScript successfully  !", savedOperationAtmDto);
        } catch (Exception e) {
            System.out.println("e===>"+e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addSenarioScript please check with your provider !");
        }
    }
    public ResponseApiJson<List<OperationAtmDto>> getAllSenarioScripts(String bankCode) {
        String idRequest = "CPS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<OperationAtm> operationAtms = senarioScriptRepository.findAllByBankCode(bankCode);
            System.out.println("senarioScripts = " + operationAtms.size());
            List<OperationAtmDto> operationAtmDtos = operationAtms.stream()
                    .map(this::mapSenarioScriptToDto)
                    .collect(Collectors.toList());
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all SenarioScripts successfully  !", operationAtmDtos);
        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllSenarioScripts please check with your provider !");
        }
    }

    private OperationAtmDto mapSenarioScriptToDto(OperationAtm operationAtm) {
        OperationAtmDto operationAtmDto = modelMapper.map(operationAtm, OperationAtmDto.class);

        List<ScriptCasesDefinitionDto> scriptCasesDefinitionDtos = operationAtm.getScriptCasesDefinitions().stream()
                .map(scriptCasesDefinition -> modelMapper.map(scriptCasesDefinition, ScriptCasesDefinitionDto.class))
                .collect(Collectors.toList());

        operationAtmDto.setScriptCasesDefinitions(scriptCasesDefinitionDtos);

        AtmCardProfilDto atmCardProfilDto = modelMapper.map(operationAtm.getCardProfile(), AtmCardProfilDto.class);
        operationAtmDto.setAtmCardProfilDto(atmCardProfilDto);

        return operationAtmDto;
    }

    public ResponseApiJson<OperationAtmDto> updateSenarioScript(Long senarioScriptId, OperationAtmDto updatedOperationAtmDto) {
        String idRequest = "UM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            OperationAtm operationAtm = senarioScriptRepository.findById(senarioScriptId)
                    .orElseThrow(() -> new EntityNotFoundException("SenarioScript with id " + senarioScriptId + " not found"));

            operationAtm.setLibelle(updatedOperationAtmDto.getLibelle());

            List<ScriptCasesDefinition> updatedScriptCasesDefinitions = updatedOperationAtmDto.getScriptCasesDefinitions().stream()
                    .map(scriptCasesDefinitionDto -> {
                        ScriptCasesDefinition scriptCasesDefinition = scriptCasesDefinitionService.findByLibelle(scriptCasesDefinitionDto.getLibelle());
                        return scriptCasesDefinition;
                    })
                    .collect(Collectors.toList());

            operationAtm.setScriptCasesDefinitions(updatedScriptCasesDefinitions);

            OperationAtm savedOperationAtm = senarioScriptRepository.save(operationAtm);

            OperationAtmDto savedOperationAtmDto = modelMapper.map(savedOperationAtm, OperationAtmDto.class);

            List<ScriptCasesDefinitionDto> scriptCasesDefinitionDtos = savedOperationAtm.getScriptCasesDefinitions().stream()
                    .map(scriptCasesDefinition -> modelMapper.map(scriptCasesDefinition, ScriptCasesDefinitionDto.class))
                    .collect(Collectors.toList());

            savedOperationAtmDto.setScriptCasesDefinitions(scriptCasesDefinitionDtos);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Updated SenarioScript successfully  !", savedOperationAtmDto);
        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateSenarioScript please check with your provider !");
        }
    }

    public ResponseApiJson<OperationAtmDto> getSenarioScriptById(Long senarioScriptId) {
        String idRequest = "GS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            OperationAtm operationAtm = senarioScriptRepository.findById(senarioScriptId)
                    .orElseThrow(() -> new EntityNotFoundException("SenarioScript with id " + senarioScriptId + " not found"));

            OperationAtmDto operationAtmDto = modelMapper.map(operationAtm, OperationAtmDto.class);

            List<ScriptCasesDefinitionDto> scriptCasesDefinitionDtos = operationAtm.getScriptCasesDefinitions().stream()
                    .map(scriptCasesDefinition -> modelMapper.map(scriptCasesDefinition, ScriptCasesDefinitionDto.class))
                    .collect(Collectors.toList());

            operationAtmDto.setScriptCasesDefinitions(scriptCasesDefinitionDtos);

            // Map the atmCardProfilDto from the SenarioScript to the SenarioScriptDto
            AtmCardProfilDto atmCardProfilDto = modelMapper.map(operationAtm.getCardProfile(), AtmCardProfilDto.class);
            operationAtmDto.setAtmCardProfilDto(atmCardProfilDto);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get SenarioScript successfully  !", operationAtmDto);
        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getSenarioScriptById please check with your provider !");
        }
    }

    public ResponseApiJson<Void> deleteSenarioScript(Long senarioScriptId) {
        String idRequest = "DS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            OperationAtm operationAtm = senarioScriptRepository.findById(senarioScriptId)
                    .orElseThrow(() -> new EntityNotFoundException("SenarioScript with id " + senarioScriptId + " not found"));
                    operationAtm.setScriptCasesDefinitions(null);
                    operationAtm.setCardProfile(null);
            senarioScriptRepository.delete(operationAtm);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleted SenarioScript successfully  !", null);
        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteSenarioScript please check with your provider !");
        }
    }


}
