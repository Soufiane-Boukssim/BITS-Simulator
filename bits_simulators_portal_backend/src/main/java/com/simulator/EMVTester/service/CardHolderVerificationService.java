package com.simulator.EMVTester.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.EMVTester.dto.Emv;
import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class CardHolderVerificationService {
    private final Logger logger = LogManager.getLogger(LogNDCController.class);



    public ResponseApiJson<List<Emv>> cardHolderVerification(Object body) {
        String idRequest = "Initiate_Application_" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();
//        logger.info("---->body");
        try {
            emvList.add(createEmvLog("Processing Restriction in Progress ..."));
            // Conversion du corps en objet JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(body, JsonNode.class);



            String tag9F08Value = findTagValue(rootNode, "9F08");
            String tag9F07Value = findTagValue(rootNode, "9F07");
            String tag5F28Value = findTagValue(rootNode, "5F28");
            String tag5F25Value = findTagValue(rootNode, "5F25");
            String tag5F24Value = findTagValue(rootNode, "5F24");

            int num=350;
            int num2=150;
            if (tag9F08Value!=null){
                emvList.add(createEmvLog(">>> Application Version ( " + num + ") Card Version ("+num2 +")"));
                //            ApplicationVersionChecking  >>> Application Version (305) Card Version (150)


                emvList.add(createEmvLog("ICC and terminal have different application versions ..."));
            }else {
                emvList.add(createEmvLog("ICC don't have application versions TAG 9F08..."));
            }


            if (tag9F07Value!=null && tag5F28Value!=null){

                emvList.add(createEmvLog("AUC : " + tag9F07Value + "(" + Hexa_To_Bin(tag9F07Value )+ ") Issuer Country (" + tag5F28Value.substring(1) + ")Terminal Country (" +  tag5F28Value.substring(1) + ")"));
                emvList.add(createEmvLog("Requested service not allowed for card product ..."));

            }else {
                emvList.add(createEmvLog("ICC Don't have Application Usage Controle TAG 9F07..."));
            }

            if (tag5F25Value!=null){
                emvList.add(createEmvLog(">>> Check Effective Date ("+tag5F25Value+") Sysdate ("+new Date()));

            }else{
                emvList.add(createEmvLog("Application not yet effective ..."));
                //emvList.add(createEmvLog("ICC Don't have Application Effective Date TAG 5F25..."));
            }
            if(tag5F24Value!=null){
                emvList.add(createEmvLog(">>> Check Expiry Date ("+tag5F24Value+") Sysdate "+new Date()));
                emvList.add(createEmvLog("Check Expiry Date Failed ("+tag5F24Value+") "));
                emvList.add(createEmvLog("Expired application ..."));
            }else {

                emvList.add(createEmvLog("ICC Don't have Application Expiration Date TAG 5F24..."));
            }


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Read cardHolderVerification  successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in Read cardHolderVerification, please check with your provider !", null);
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
