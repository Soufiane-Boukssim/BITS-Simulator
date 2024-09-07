package com.simulator.EMVTester.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.EMVTester.dto.Emv;
import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
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
public class AuthorisationRequestService {

    @Autowired
    private EMVProfileRepository profileRepository;

    @Autowired
    private TransactionConfigRepository transactionConfigRepository;


    @Autowired
    private TerminalConfigRepository terminalConfigRepository;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);


    public ResponseApiJson<List<Emv>> AuthorisationRequest(Object body) {
        String idRequest = "Authorisation_Request" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();
//        logger.info("---->body");
        try {
            emvList.add(createEmvLog("Card Action Analyser in Progress ..."));


            // Conversion du corps en objet JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(body, JsonNode.class);

            // Fonction pour trouver la valeur correspondante à "Name": "9F38"
            String tag9F38Value = findTagValue(rootNode, "9F38");


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "AuthorisationRequest  successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in  AuthorisationRequest, please check with your provider !", null);
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
