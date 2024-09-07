package com.simulator.atm.services.script;

import com.simulator.atm.dto.AtmCardProfilDto;
import com.simulator.atm.dto.SenarioAtmDto;
import com.simulator.atm.dto.OperationAtmDto;
import com.simulator.atm.model.AtmCardProfil;
import com.simulator.atm.model.SenarioAtm;
import com.simulator.atm.model.OperationAtm;
import com.simulator.atm.repositories.reposcript.AtmCardProfilRepository;
import com.simulator.atm.repositories.reposcript.SenarioAtmRepository;
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
public class SenarioAtmService {

    @Autowired
    private OperationAtmRepostory senarioScriptRepository;
    @Autowired
    private SenarioAtmRepository senarioAtmRepository;
    @Autowired
    private AtmCardProfilRepository cardProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseApiJson<SenarioAtmDto> addSenario(SenarioAtmDto senarioRequestDto) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            SenarioAtm senarioAtm = modelMapper.map(senarioRequestDto, SenarioAtm.class);

            AtmCardProfil atmCardProfil = cardProfileRepository.findFirstByCardNo(senarioRequestDto.getIdCardProfil().getCardNo());
            if (atmCardProfil == null) {
                throw new RuntimeException("AtmCardProfil with this issuerCode does not exist");
            }
            senarioAtm.setCardProfile(atmCardProfil);

            List<OperationAtm> operationAtms = senarioRequestDto.getSenarioScripts().stream()
                    .map(senarioScriptDto -> {
                        OperationAtm operationAtm = senarioScriptRepository.findByLibelle(senarioScriptDto.getLibelle());
                        System.out.println("scriptCasesDefinition = " + operationAtm.getLibelle());
                        return operationAtm;
                    })
                    .collect(Collectors.toList());

            senarioAtm.setOperationAtms(operationAtms);
            System.out.println("size 1 = " + senarioAtm.getOperationAtms().size());
            SenarioAtm save = senarioAtmRepository.save(senarioAtm);
            System.out.println("size 2 = " + save.getOperationAtms().size());
            SenarioAtmDto saved = modelMapper.map(save, SenarioAtmDto.class);

            List<OperationAtmDto> operationAtmDtos = operationAtms.stream()
                    .map(senarioScript -> modelMapper.map(senarioScript, OperationAtmDto.class))
                    .collect(Collectors.toList());

            saved.setSenarioScripts(operationAtmDtos);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted SenarioScript successfully  !", saved);
        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addSenarioScript please check with your provider !");
        }
    }
    public ResponseApiJson<List<SenarioAtmDto>> getAllSenarios(String bankCode) {
        String idRequest = "CPS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<SenarioAtm> senarioAtms = senarioAtmRepository.findByBankCode(bankCode);
            List<SenarioAtmDto> senarioAtmDtos = senarioAtms.stream()
                    .map(this::mapSenarioToDto)
                    .collect(Collectors.toList());
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Senarios successfully  !", senarioAtmDtos);
        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllSenarios please check with your provider !");
        }
    }

    private SenarioAtmDto mapSenarioToDto(SenarioAtm senarioAtm) {
        SenarioAtmDto senarioAtmDto = modelMapper.map(senarioAtm, SenarioAtmDto.class);

        List<OperationAtmDto> operationAtmDtos = senarioAtm.getOperationAtms().stream()
                .map(senarioScript -> {
                    OperationAtmDto operationAtmDto = modelMapper.map(senarioScript, OperationAtmDto.class);
                    AtmCardProfilDto atmCardProfilDto = modelMapper.map(senarioScript.getCardProfile(), AtmCardProfilDto.class);
                    operationAtmDto.setAtmCardProfilDto(atmCardProfilDto);
                    return operationAtmDto;
                })
                .collect(Collectors.toList());

        senarioAtmDto.setSenarioScripts(operationAtmDtos);


        return senarioAtmDto;
    }

    public ResponseApiJson<SenarioAtmDto> updateSenario(Long senarioId, SenarioAtmDto updatedSenarioAtmDto) {
        String idRequest = "UM_" + new Date().getTime() + (Math.random() * 9999);
        try {

            SenarioAtm senarioAtm = senarioAtmRepository.findById(senarioId)
                    .orElseThrow(() -> new EntityNotFoundException("Senario with id " + senarioId + " not found"));

            senarioAtm.setLibelle(updatedSenarioAtmDto.getLibelle());

            AtmCardProfil atmCardProfil = cardProfileRepository.findFirstByCardNo(updatedSenarioAtmDto.getIdCardProfil().getCardNo());
            if (atmCardProfil == null) {
                throw new RuntimeException("AtmCardProfil with this issuerCode does not exist");
            }
            senarioAtm.setCardProfile(atmCardProfil);

            List<OperationAtm> updatedOperationAtms = updatedSenarioAtmDto.getSenarioScripts().stream()
                    .map(senarioScriptDto -> {
                        OperationAtm operationAtm = senarioScriptRepository.findByLibelle(senarioScriptDto.getLibelle());
                        return operationAtm;
                    })
                    .collect(Collectors.toList());

            senarioAtm.setOperationAtms(updatedOperationAtms);

            SenarioAtm savedSenarioAtm = senarioAtmRepository.save(senarioAtm);

            SenarioAtmDto savedSenarioAtmDto = modelMapper.map(savedSenarioAtm, SenarioAtmDto.class);

            List<OperationAtmDto> operationAtmDtos = savedSenarioAtm.getOperationAtms().stream()
                    .map(senarioScript -> modelMapper.map(senarioScript, OperationAtmDto.class))
                    .collect(Collectors.toList());

            savedSenarioAtmDto.setSenarioScripts(operationAtmDtos);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Updated Senario successfully  !", savedSenarioAtmDto);
        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateSenario please check with your provider !");
        }
    }

    public ResponseApiJson<SenarioAtmDto> getSenarioById(Long senarioId) {
        String idRequest = "GS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            SenarioAtm senarioAtm = senarioAtmRepository.findById(senarioId)
                    .orElseThrow(() -> new EntityNotFoundException("Senario with id " + senarioId + " not found"));

            SenarioAtmDto senarioAtmDto = modelMapper.map(senarioAtm, SenarioAtmDto.class);

            List<OperationAtmDto> operationAtmDtos = senarioAtm.getOperationAtms().stream()
                    .map(senarioScript -> {
                        OperationAtmDto operationAtmDto = modelMapper.map(senarioScript, OperationAtmDto.class);
                        AtmCardProfilDto atmCardProfilDto = modelMapper.map(senarioScript.getCardProfile(), AtmCardProfilDto.class);
                        operationAtmDto.setAtmCardProfilDto(atmCardProfilDto);
                        return operationAtmDto;
                    })
                    .collect(Collectors.toList());

            senarioAtmDto.setSenarioScripts(operationAtmDtos);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get Senario successfully  !", senarioAtmDto);
        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getSenarioById please check with your provider !");
        }
    }

    public ResponseApiJson<Void> deleteSenario(Long senarioId) {
        String idRequest = "DS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            SenarioAtm senarioAtm = senarioAtmRepository.findById(senarioId)
                    .orElseThrow(() -> new EntityNotFoundException("Senario with id " + senarioId + " not found"));
                    senarioAtm.setOperationAtms(null);
                    senarioAtm.setCardProfile(null);
            senarioAtmRepository.delete(senarioAtm);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleted Senario successfully  !", null);
        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteSenario please check with your provider !");
        }
    }


}
