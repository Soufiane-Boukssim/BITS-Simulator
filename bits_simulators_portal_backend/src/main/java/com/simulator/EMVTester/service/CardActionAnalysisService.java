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
import java.util.regex.Pattern;

@Service
public class CardActionAnalysisService {

    @Autowired
    private EMVProfileRepository profileRepository;

    @Autowired
    private TransactionConfigRepository transactionConfigRepository;


    @Autowired
    private TerminalConfigRepository terminalConfigRepository;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);

    public ResponseApiJson<List<Emv>> cardActionAnalysis(Object body) {
        String idRequest = "CARD_ACTION_ANALYSIS" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();
//        logger.info("---->body");
        try {
            emvList.add(createEmvLog("Terminal Action Analyser in Progress ..."));

            // Conversion du corps en objet JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(body, JsonNode.class);

            // Fonction pour trouver la valeur correspondante à "Name": "9F38"
            String tag9F0DValue = findTagValue(rootNode, "9F0D");
            String tag9F0EValue = findTagValue(rootNode, "9F0E");
            String tag9F0FValue = findTagValue(rootNode, "9F0F");
            String TerminalTACR ="";
            String TerminalTACO ="";
            boolean _Refuse =false;
            if (tag9F0DValue==null){
                tag9F0DValue="0000000000";
            }
            if (tag9F0EValue==null){
                tag9F0EValue="0000000000";
            }
            if (tag9F0FValue==null){
                tag9F0FValue="0000000000";
            }



            emvList.add(createEmvLog("Check Issuer Action Code - Denial [" + tag9F0DValue + "]"));

            emvList.add(createEmvLog("AC :" + Hexa_To_Bin(tag9F0DValue) + " "));

            emvList.add(createEmvLog(">>> Occured in Index [" + 9 + " ]"));

            Optional<EMVProfile> optionalActiveProfile = profileRepository.findByActiveProfile("Y");
            if (optionalActiveProfile.isPresent()) {
                EMVProfile activeProfile = optionalActiveProfile.get();
                String codeProfile = activeProfile.getCodeProfile();
                logger.info("code profile activeProfile " + codeProfile);

                Optional<TerminalConfig> terminalConfigOptional = terminalConfigRepository.findByCodeProfile(codeProfile);
                if (terminalConfigOptional.isPresent()) {
                    TerminalConfig terminalConfig = terminalConfigOptional.get();
                    TerminalTACR = terminalConfig.getTerminalTacr();
                    TerminalTACO = terminalConfig.getTerminalTaco();
                }
            }


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " card Action Analysis  successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in card Action Analysis  please check with your provider !", null);
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

    // Fonction pour convertir une valeur hexadécimale en binaire
    private String Hexa_To_Bin(String pHexa) {
        String text = pHexa.toUpperCase();
        if (Is_Hexa(pHexa)) {
            text = text.replace("0", "0000");
            text = text.replace("1", "0001");
            text = text.replace("2", "0010");
            text = text.replace("3", "0011");
            text = text.replace("4", "0100");
            text = text.replace("5", "0101");
            text = text.replace("6", "0110");
            text = text.replace("7", "0111");
            text = text.replace("8", "1000");
            text = text.replace("9", "1001");
            text = text.replace("A", "1010");
            text = text.replace("B", "1011");
            text = text.replace("C", "1100");
            text = text.replace("D", "1101");
            text = text.replace("E", "1110");
            return text.replace("F", "1111");
        }

        return null;
    }
    // Fonction pour vérifier si une chaîne est au format hexadécimal
    private boolean Is_Hexa(String input) {
        // Utiliser une expression régulière pour vérifier si la chaîne est au format hexadécimal
        String hexPattern = "^[0-9A-Fa-f]+$";
        return Pattern.matches(hexPattern, input);
    }


}
