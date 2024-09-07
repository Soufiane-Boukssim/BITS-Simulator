package com.simulator.EMVTester.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.EMVTester.dto.Emv;
import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.EMVProfile;
import com.simulator.smartICC.models.TerminalConfig;
import com.simulator.smartICC.repositories.EMVProfileRepository;
import com.simulator.smartICC.repositories.TerminalConfigRepository;
import com.simulator.smartICC.repositories.TransactionConfigRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TerminalActionAnalysisService {

    private final Logger logger = LogManager.getLogger(LogNDCController.class);


    @Autowired
    private EMVProfileRepository profileRepository;

    @Autowired
    private TransactionConfigRepository transactionConfigRepository;


    @Autowired
    private TerminalConfigRepository terminalConfigRepository;





    public ResponseApiJson<List<Emv>> terminalActionAnalysis(Object body) {
        String idRequest = "Action_Analysis_Application_" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();
//        logger.info("---->body");
        try {
            emvList.add(createEmvLog("Risk Management in Progress ..."));

            // Conversion du corps en objet JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(body, JsonNode.class);

            // Fonction pour trouver la valeur correspondante
            String tagA5Value = findTagValue(rootNode, "A5");
            String tag9F14Value = findTagValue(rootNode, "9F14");
            String tag9F23Value = findTagValue(rootNode, "9F23");
            String tag9F13Value = findTagValue(rootNode, "9F13");
            String tag9F36Value = findTagValue(rootNode, "9F36");
            Integer TerminalFloor=0;
            Integer pAmount = 0;
            Integer TerminalValue=0;
            Integer TerminalMax =0;
            Integer TerminalMin =0;
            String TerminalBaseEmv="";
            Boolean FloorLimitChecking=false;
            Boolean RandomTrxSelection=false;
            if (tagA5Value!=null){
                Optional<EMVProfile> optionalActiveProfile = profileRepository.findByActiveProfile("Y");
                if (optionalActiveProfile.isPresent()) {
                    EMVProfile activeProfile = optionalActiveProfile.get();
                    String codeProfile = activeProfile.getCodeProfile();
                    logger.info("code profile activeProfile " + codeProfile);

                    Optional<TerminalConfig> terminalConfigOptional  = terminalConfigRepository.findByCodeProfile(codeProfile);
                    if (terminalConfigOptional.isPresent()) {
                        TerminalConfig terminalConfig = terminalConfigOptional.get();
                        TerminalFloor = terminalConfig.getTerminalFloor();
                        TerminalBaseEmv=terminalConfig.getTerminalBaseEmv();
                        TerminalValue=terminalConfig.getTerminalValue();
                        TerminalMax=terminalConfig.getTerminalMax();
                        TerminalMin=terminalConfig.getTerminalMin();
                    } else {
                        // Aucune configuration de transaction trouvée pour ce code de profil
                        logger.info("Aucune configuration de transaction trouvée pour le code de profil: " + codeProfile);
                    }
                }
                emvList.add(createEmvLog("Check Floor Limit Amount[" + pAmount + "] Floor [" + TerminalFloor + "]"));
                emvList.add(createEmvLog("Check Random Transaction Selection"));
                Random random = new Random();
                if (TerminalFloor!=TerminalValue){
                  double num=(pAmount-TerminalValue)/(TerminalFloor-TerminalValue);
                  double num2=(TerminalMax-TerminalMin)*num+TerminalMin;
                  if (random.nextDouble(0,99)<num2){
                      RandomTrxSelection=true;
                  }else {
//                      emvList.add(createEmvLog("Transaction selected randomly for online processing..."));
                  }
                }
            }


            if (TerminalBaseEmv=="M"&& pAmount>TerminalFloor){
               // logger.info("false");
                FloorLimitChecking=false;
            }else {
                FloorLimitChecking=true;
            }
            if (TerminalBaseEmv=="S"&& pAmount>TerminalFloor){
                // logger.info("false");
                FloorLimitChecking=true;
            }
            if (FloorLimitChecking == true) {
//                emvList.add(createEmvLog("Transaction exceeds floor limit..."));
            }

//            if (tag9F14Value!=null && tag9F23Value!=null){
//                if (tag9F36Value==null )
//            }


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Terminal Action Analysis  successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in Terminal Action Analysis ApplicationData, please check with your provider !", null);
        }
    }

    private Emv createEmvLog(String description) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return new Emv(sdf.format(new Date()), description);
    }


    // Fonction récursive pour parcourir la structure JSON et trouver la valeur de l'élément correspondant à "Name"
    private String findTagValue(JsonNode node, String tagName) {
        if (node.isArray()) {
            for (JsonNode arrayElement : node) {
                String value = findTagValue(arrayElement, tagName);
                if (value != null) {
                    return value;
                }
            }
        } else if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                if (field.getKey().equals("Name") && field.getValue().asText().equals(tagName)) {
                    JsonNode valueNode = node.get("Value");
                    return valueNode != null ? valueNode.asText() : null;
                } else {
                    String value = findTagValue(field.getValue(), tagName);
                    if (value != null) {
                        return value;
                    }
                }
            }
        }
        return null;
    }



}
