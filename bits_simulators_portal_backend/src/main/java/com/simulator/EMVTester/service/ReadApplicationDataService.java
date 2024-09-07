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

@Service
public class ReadApplicationDataService {

    private final Logger logger = LogManager.getLogger(LogNDCController.class);



    public ResponseApiJson<List<Emv>> ReadApplicationData( Object body) {
        String idRequest = "Initiate_Application_" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();
//        logger.info("---->body");
        try {
            emvList.add(createEmvLog("Get application data in progress ..."));
            emvList.add(createEmvLog("Get Card data in progress ... "));

            // Conversion du corps en objet JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(body, JsonNode.class);

            // Fonction pour trouver la valeur correspondante à "Name": "9F38"
            String tag9F38Value = findTagValue(rootNode, "9F38");
            String tag88Value = findTagValue(rootNode, "88");

            // Ajouter le log en fonction de la valeur trouvée
            if (tag9F38Value != null) {
                emvList.add(createEmvLog("Tag_9F38 : [ " + tag9F38Value + " ]"));
            }
            if (tag9F38Value == null) {
                emvList.add(createEmvLog("NULL : [  ]"));
            }

            if (tag88Value != null) {
                emvList.add(createEmvLog("Tag_88 : [ " + tag88Value + " ]"));
            } else {
                emvList.add(createEmvLog("Tag_88 : [ NULL ]"));
            }
            if (tag88Value != null) {
                emvList.add(createEmvLog("Read Record in progress ... : [ 08010101" + tag88Value + " ]"));
            } else {
                emvList.add(createEmvLog("Tag_88 : [ NULL ]"));
            }


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Read ApplicationData  successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in Read ApplicationData, please check with your provider !", null);
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
