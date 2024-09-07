package com.simulator.EMVTester.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.EMVTester.dto.Emv;
import com.simulator.EMVTester.dto.HistoryPara;
import com.simulator.EMVTester.repository.HistoryPAraREpository;
import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserManagement;
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
import java.util.regex.Pattern;

@Service
public class IssuerResponseService {
    @Autowired
    private EMVProfileRepository profileRepository;

    @Autowired
    private TransactionConfigRepository transactionConfigRepository;


    @Autowired
    private TerminalConfigRepository terminalConfigRepository;
    @Autowired
    private HistoryPAraREpository historyParaRepository;


    private final Logger logger = LogManager.getLogger(LogNDCController.class);

    @Autowired
    GlobalVars globalVars;
    public ResponseApiJson<List<Emv>> issuerResponse(Object body) {
        String idRequest = "Issuer_Response" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();
//        logger.info("---->body");
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            emvList.add(createEmvLog("Authorisation Request in Progress ..."));


            // Conversion du corps en objet JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(body, JsonNode.class);
            Boolean  _Declined=false;
            Boolean _Able_To_GoOnline=true;
            Boolean _IssAccepted=true;
            Boolean _SendAutheticationData=true;

             String _Cryptogram="";
            String _CVR="";

            // Fonction pour trouver la valeur correspondante à "Name": "9F38"
            String tag9F27Value = findTagValue(rootNode, "9F27");
            String tag9F26Value = findTagValue(rootNode, "9F26");
            String tag9F10Value = findTagValue(rootNode, "9F10");

            if (tag9F27Value!=null){
                emvList.add(createEmvLog("Type de Cryptograme Returned [" + Hexa_To_Bin(tag9F27Value) + "]"));

                switch (tag9F27Value)
                {
                    case "00":
                        _Cryptogram = tag9F26Value;
                        _Declined = true;
                        break;
                    case "40":
                        _Cryptogram = tag9F26Value;
                        _Declined = false;
                        break;
                    default:
                        _Able_To_GoOnline = true;
                        _Cryptogram = tag9F26Value;
                        _Declined = false;
                        break;
                }

                switch (tag9F10Value)
                {
                    case "VIS":
                        _CVR = tag9F10Value.substring(8, 6);
                        break;
                    case "MCL2":
                        _CVR = tag9F10Value.substring(5, 6);
                        break;
                    case "MCS2":
                        _CVR = tag9F10Value.substring(4, 4);
                        break;
                    case "MC4":
                        _CVR = tag9F10Value.substring(3, 6);
                        break;
                    case "VISCCD":
                        _CVR = tag9F10Value.substring(4, 10);
                        break;
                    default:
                        _CVR = "0000000000";
                        break;
                }



                emvList.add(createEmvLog("CVR IS  [" + _CVR + "] Cryptogram [" + tag9F10Value + "]"));
            }else {
                emvList.add(createEmvLog("Error in Authorisation Request [La référence d'objet n'est pas définie à une instance d'un objet.]"));
            }
            if (tag9F26Value!=null){
                emvList.add(createEmvLog("Cryptograme Returned [" + Hexa_To_Bin(tag9F26Value) + "]"));
            }
            if (tag9F10Value!=null){
                emvList.add(createEmvLog("Issuer Application Data [" + Hexa_To_Bin(tag9F10Value) + "]"));
            }


            // Création d'un nouvel objet history_para
            HistoryPara historyPara = new HistoryPara();
            historyPara.setUserId(userM.get().getUserCode());
            historyPara.set_Declined(_Declined);
            historyPara.set_Able_To_GoOnline(_Able_To_GoOnline);
            historyPara.set_IssAccepted(_IssAccepted);
            historyPara.set_SendAutheticationData(_SendAutheticationData);

            // Sauvegarde de l'objet dans la base de données
            historyParaRepository.save(historyPara);


            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Issuer_Response  successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in  Issuer_Response, please check with your provider !", null);
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

