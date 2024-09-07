package com.simulator.atm.services.webService;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.atm.dto.AtmCardProfilDto;
import com.simulator.atm.dto.FieldCasesDto;
import com.simulator.atm.dto.OperationAtmDto;
import com.simulator.atm.dto.ScriptCasesDefinitionDto;
import com.simulator.atm.model.ATMfield;
import com.simulator.atm.model.enm.TypeDefinitionScript;
import com.simulator.atm.repositories.repoatm.TerminalLogRepository;
import com.simulator.atm.services.atmm.TerminalLogService;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.globalService.AuthenticationService;
import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ScriptConversionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptConversionService.class);
    private static final int MAX_LOG_SEQUENCE_LENGTH = 5;


    private final org.apache.logging.log4j.Logger loggerr= LogManager.getLogger(LogNDCController.class);

    @Autowired
    private TerminalLogRepository terminalLogRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TerminalLogService terminalLogService;

    public ResponseApiJson<String> processSenarioScript(OperationAtmDto operationAtmDto) {
        String idRequest = "PS_" + new Date().getTime() + (Math.random() * 9999);
        loggerr.info("data operationAtmDto ["+operationAtmDto);
        try {
            StringBuilder resultIn = new StringBuilder();
            StringBuilder resultOut = new StringBuilder();
            for (ScriptCasesDefinitionDto scriptCasesDefinition : operationAtmDto.getScriptCasesDefinitions()) {
                TypeDefinitionScript typeScript = scriptCasesDefinition.getTypeScript();

                AtmCardProfilDto pCardProfile = operationAtmDto.getAtmCardProfilDto();

                loggerr.info("typeScript: " + typeScript);
                loggerr.info("scriptCasesDefinition ==>"+scriptCasesDefinition );
                loggerr.info("pCardProfile ==>"+pCardProfile );
                if (TypeDefinitionScript.TRANSACTIONMESSAGE.equals(typeScript)) {
                    String transactionResult = buildTrxReq(scriptCasesDefinition, pCardProfile);
                    resultIn.append(transactionResult);
                } else if (TypeDefinitionScript.READYMESSAGE.equals(typeScript)) {
                    String readyMessageResult = buildSolicitedMsg(scriptCasesDefinition);
                    resultOut.append(readyMessageResult);
                }
            }

            // Call saveMessages only if both resultIn and resultOut are not empty
            if (resultIn.length() > 0 && resultOut.length() > 0) {
                terminalLogService.saveMessages(resultIn.toString(), resultOut.toString());
            }

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Processed SenarioScript successfully  !", resultIn.toString() + resultOut.toString());
        } catch (Exception e) {
            LOGGER.error("Error processing scenario script", e);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in processSenarioScript please check with your provider !");
        }
    }


//    public void saveToTerminalLog(String result, UserManagement currentUser) {
//        TerminalLog terminalLog = new TerminalLog();
//        terminalLog.setLogEData(result);
//
//        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        Date date = new Date();
//        terminalLog.setLogEDate(formatter.format(date));
//
//        TerminalParamLogId id = new TerminalParamLogId();
//        id.setTermCode(UUID.randomUUID().toString().substring(0, MAX_LOG_SEQUENCE_LENGTH));
//
//        id.setBankCode(currentUser.getUserBankCode());
//
//        terminalLog.setId(id);
//
//        String logSequence = UUID.randomUUID().toString();
//        if (logSequence.length() > MAX_LOG_SEQUENCE_LENGTH) {
//            logSequence = logSequence.substring(0, MAX_LOG_SEQUENCE_LENGTH);
//        }
//        terminalLog.setLogSequence(logSequence);
//
//        terminalLogRepository.save(terminalLog);
//    }
    public String buildSolicitedMsg(ScriptCasesDefinitionDto scriptCasesDefinition) {
        String pData = null;
        String TimeVariantNumber = "";
        String fs = String.valueOf('\u001c');

        String lunoValue = getFieldCaseValue(scriptCasesDefinition, "LUNO");
        if (lunoValue != null && !lunoValue.isEmpty()) {
            pData = String.join(fs, "22", fs ,lunoValue, fs, fs, TimeVariantNumber);
        }

        String sdscValue = getFieldCaseValue(scriptCasesDefinition, "SDSC");
        if (sdscValue != null && !sdscValue.isEmpty()) {
            pData = (pData == null ? "" : pData) + fs + sdscValue;
        }

        String sinfValue = getFieldCaseValue(scriptCasesDefinition, "SINF");
        if (sinfValue != null && !sinfValue.isEmpty()) {
            pData = (pData == null ? "" : pData) + fs + sinfValue;
        }

        return pData;
    }

    public String buildTrxReq (ScriptCasesDefinitionDto scriptCasesDefinition, AtmCardProfilDto pCardProfile) {
        String pData = "";
        String TimeVariantNumber = "";
        String fs = String.valueOf('\u001c');
        String gs = String.valueOf('\u001d');

        String lunoValue = getFieldCaseValue(scriptCasesDefinition, "LUNO");
        if (lunoValue != null && !lunoValue.isEmpty()) {
            pData = String.join(fs, "11", fs ,lunoValue, fs, fs, TimeVariantNumber);
        }


        String trtfValue = getFieldCaseValue(scriptCasesDefinition, "TRTF");
        if (trtfValue != null && !trtfValue.isEmpty()) {
            pData = pData + fs + trtfValue;
        }


        String mncValue = getFieldCaseValue(scriptCasesDefinition, "MNC");
        if (mncValue != null && !mncValue.isEmpty()) {
            pData = pData + fs + mncValue;
        }


        String tr03Value = getFieldCaseValue(scriptCasesDefinition, "TR03");
        if (tr03Value != null && !tr03Value.isEmpty()) {
            pData += tr03Value + fs;
        }

        String ocdValue = getFieldCaseValue(scriptCasesDefinition, "OCD");
        if (ocdValue != null && !ocdValue.isEmpty()) {
            pData += ocdValue + fs;
        }

        String amefValue = getFieldCaseValue(scriptCasesDefinition, "AMEF");
        if (amefValue != null && !amefValue.isEmpty()) {
            pData += amefValue + fs;
        }

        String bufferBValue = getFieldCaseValue(scriptCasesDefinition, "GPBB");
        if (bufferBValue != null && !bufferBValue.isEmpty()) {
            pData += bufferBValue + fs;
        }
        String bufferCValue = getFieldCaseValue(scriptCasesDefinition, "GPBC");
        if (bufferCValue != null && !bufferCValue.isEmpty()) {
            pData += bufferCValue + fs;
        }

        String track2Value = getFieldCaseValue(scriptCasesDefinition, "TR02");
        if (track2Value != null && !track2Value.isEmpty()) {
            LOGGER.info("track2Value: {}", track2Value);
            if(track2Value.equals("FROM_CARD_PROFILE")) {
                LOGGER.info("track2Value tr02: {}", pCardProfile.getTrack2());
                pData += pCardProfile.getTrack2() + fs;
            }
//            else if (track2Value.equals("GENERATED_BY_SYSTEM")){
//                LOGGER.info("track2Value tr01 GENERATED_BY_SYSTEM: {}", pCardProfile.getTrack1());
//                pData += "1" + pCardProfile.getTrack1() + fs;
//            }
            else {
                pData += ";" + track2Value + "?" + fs;
            }
        }

        String track1Value = getFieldCaseValue(scriptCasesDefinition, "TR01");
        if (track1Value != null && !track1Value.isEmpty()) {
//            LOGGER.info("track1Value: {}", track1Value);
            loggerr.info("track1Value :["+track1Value+"]");
              if(track1Value.equals("FROM_CARD_PROFILE"))
              {
                  loggerr.info("track1Value tr01 FROM_CARD_PROFILE :", pCardProfile.getTrack1());
                pData += "1" + pCardProfile.getTrack1() + fs;
              }
//              else if (track1Value.equals("GENERATED_BY_SYSTEM")){
//                  loggerr.info("track1Value tr01 GENERATED_BY_SYSTEM: {}", pCardProfile.getTrack1());
//                pData += "1" + pCardProfile.getTrack1() + fs;
//              }
              else {
                pData += "1" +track1Value + fs;
              }
        } else {
            pData += fs;
        }

        String tsdiValue = getFieldCaseValue(scriptCasesDefinition, "TSDI");
        if(tsdiValue != null && !tsdiValue.isEmpty()) {
            pData += "2"+ tsdiValue + fs;
        }

        String cspuValue = getFieldCaseValue(scriptCasesDefinition, "CSPU");
        if(cspuValue != null && !cspuValue.isEmpty()) {
            pData += fs+ "U"+ cspuValue;
        }

        String cspvValue = getFieldCaseValue(scriptCasesDefinition, "CSPV");
        if(cspvValue != null && !cspvValue.isEmpty()) {
            pData += fs+ "V"+ cspvValue;
        }

        String vcdwValue = getFieldCaseValue(scriptCasesDefinition, "VCDW");
        if(vcdwValue != null && !vcdwValue.isEmpty()) {
            pData += fs+ "W"+ vcdwValue;
        }

        String vcdxValue = getFieldCaseValue(scriptCasesDefinition, "VCDX");
        if(vcdxValue != null && !vcdxValue.isEmpty()) {
            pData += fs+ "X"+ vcdxValue;
        }

        String vcdyValue = getFieldCaseValue(scriptCasesDefinition, "VCDY");
        if(vcdyValue != null && !vcdyValue.isEmpty()) {
            pData += fs+ "Y"+ vcdyValue;
        }

        String vcdzValue = getFieldCaseValue(scriptCasesDefinition, "VCDZ");
        if(vcdzValue != null && !vcdzValue.isEmpty()) {
            pData += fs+ "Z"+ vcdzValue;
        }

        String vcd1Value = getFieldCaseValue(scriptCasesDefinition, "VCD1");
        if(vcd1Value != null && !vcd1Value.isEmpty()) {
            pData += fs+ "["+ vcd1Value;
        }

        String vcd2Value = getFieldCaseValue(scriptCasesDefinition, "VCD2");
        if(vcd2Value != null && !vcd2Value.isEmpty()) {
            pData += fs+ "\\"+ vcd2Value;
        }

        String devwValue = getFieldCaseValue(scriptCasesDefinition, "DEVW");
        if(devwValue != null && !devwValue.isEmpty()) {
            pData += fs+ "w"+ devwValue;
        }

        String docdValue = getFieldCaseValue(scriptCasesDefinition, "DOCD");
        if(docdValue != null && !docdValue.isEmpty()) {
            pData += fs+ "a"+ docdValue;
        }

        String fideValue = getFieldCaseValue(scriptCasesDefinition, "FIDE");
        if(fideValue != null && !fideValue.isEmpty()) {
            pData += fs+ "e"+ fideValue;
        }


        LOGGER.info("pData length: {}", pData.length());
       return pData;

    }

    private String getFieldCaseValue(ScriptCasesDefinitionDto scriptCasesDefinition, String idATMfield) {
        for (FieldCasesDto fieldCase : scriptCasesDefinition.getFieldCases()) {
            for (ATMfield atmField : fieldCase.getAtmField()) {
                if (atmField.getIdATMfield().equals(idATMfield)) {
                    return fieldCase.getValue();
                }
            }
        }

        return null;
    }

}
