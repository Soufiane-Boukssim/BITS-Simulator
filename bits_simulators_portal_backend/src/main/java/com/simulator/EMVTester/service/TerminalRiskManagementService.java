package com.simulator.EMVTester.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simulator.EMVTester.dto.Emv;
import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.smartICC.models.EMVProfile;
import com.simulator.smartICC.models.TerminalConfig;
import com.simulator.smartICC.models.TransactionConfig;
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
public class TerminalRiskManagementService {

    @Autowired
    private EMVProfileRepository profileRepository;

    @Autowired
    private TransactionConfigRepository transactionConfigRepository;


    @Autowired
    private TerminalConfigRepository terminalConfigRepository;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);


    public ResponseApiJson<List<Emv>> terminalRiskManagement(Object body) {
        String idRequest = "Initiate_Application_" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();
//        logger.info("---->body");
        try {
            emvList.add(createEmvLog("Cardholder verification in Progress ..."));


            // Conversion du corps en objet JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(body, JsonNode.class);

            String tag8EValue = findTagValue(rootNode, "8E");

            if (tag8EValue!=null){

                emvList.add(createEmvLog("Retrieving CVM List [" +tag8EValue+ "]"));


                String extractedPart = tag8EValue.substring(16);
                String pMethode = extractedPart.substring(0, 2);
                String pCondition = extractedPart.substring(2, 4);
                String Terminal_Config = ""; // Déclarer ici pour une portée globale
                Integer pin = 0; // Déclarer ici pour une portée globale
                Integer pAmount = 0; // Déclarer ici pour une portée globale
                String trxType = ""; // Déclarer ici pour une portée globale

                String _AmountX = tag8EValue.substring(0, 8);
                String _AmountY = tag8EValue.substring(8, 8);
                // Récupérer le profil actif
                Optional<EMVProfile> optionalActiveProfile = profileRepository.findByActiveProfile("Y");
                if (optionalActiveProfile.isPresent()) {
                    EMVProfile activeProfile = optionalActiveProfile.get();
                    String codeProfile = activeProfile.getCodeProfile();
                    logger.info("code profile activeProfile " + codeProfile);
                    // Rechercher les informations de transaction associées au code de profil
                    Optional<TransactionConfig> transactionConfigOptional  = transactionConfigRepository.findByCodeProfile(codeProfile);
                    if (transactionConfigOptional.isPresent()) {
                        TransactionConfig transactionConfig = transactionConfigOptional.get();
                         trxType = transactionConfig.getTrxType();
                         pin = transactionConfig.getTrxPin();
                        pAmount = transactionConfig.getTrxAmt();
                        logger.info("Transaction Config: Trx_Type=" + trxType);
                    } else {
                        // Aucune configuration de transaction trouvée pour ce code de profil
                        logger.info("Aucune configuration de transaction trouvée pour le code de profil: " + codeProfile);
                    }

                    Optional<TerminalConfig> terminalConfigOptional  = terminalConfigRepository.findByCodeProfile(codeProfile);
                    if (terminalConfigOptional.isPresent()) {
                        TerminalConfig terminalConfig = terminalConfigOptional.get();
                         Terminal_Config = terminalConfig.getTerminalConfig();

//                        logger.info("Transaction Config: Trx_Type=" + Terminal_Config);
                    } else {
                        // Aucune configuration de transaction trouvée pour ce code de profil
                        logger.info("Aucune configuration de transaction trouvée pour le code de profil: " + codeProfile);
                    }

                    emvList.add(createEmvLog(">>> Verify Methode [" + pMethode + "] Condition [" + pCondition + "] Support [" + Terminal_Config + "]Pin [" + pin + "] Trx Type [" + trxType + "]"));

                    String  text = Hexa_To_Bin(pMethode);
                    logger.info("----->text"+text);
                    logger.info("----->textV2"+text.substring(2));
                    Boolean flag=false;


                    switch (pCondition){
                        case "00":
                            flag = true;
                            break;
                        case "01":
                            if (trxType == "D" || trxType == "C")
                            {
                                flag = true;
                            }

                            break;
                        case "02":
                            if (trxType != "C")
                            {
                                flag = true;
                            }

                            break;
                        case "03":
                            flag = true;
                            break;
                        case "04":
                            if (trxType != "D" && trxType != "C")
                            {
                                flag = true;
                            }
                            break;
                        case "05":
                            if (trxType == "D" || trxType == "C")
                            {
                                flag = true;
                            };
                            break;
                        case "06":
                            if (Integer.parseInt(_AmountX) < pAmount)
                            {
                                flag = true;
                            };
                            break;
                        case "07":
                            if (Integer.parseInt(_AmountX) > pAmount)
                            {
                                flag = true;
                            }

                            break;
                        case "08":
                            if (Integer.parseInt(_AmountY) > pAmount)
                            {
                                flag = true;
                            }

                            break;
                        case "09":
                            if (Integer.parseInt(_AmountY) < pAmount)
                            {
                                flag = true;
                            }

                            break;
                    }

                    logger.info("----------> test switch  :"+flag);
                    if (flag ){
                        switch (text.substring(2))
                        {
                            case "000000":
                                emvList.add(createEmvLog(">>> Fail CVM processing !!"));
                                break;

                            case "000001":
                                emvList.add(createEmvLog(">>> Enciphered PIN verified online !! "));
                                break;
                            case "000011":
                                if (Terminal_Config == "OF" || Terminal_Config == "NF")
                                {

                                    emvList.add(createEmvLog(">>> Offline Verification, Plaintext PIN will be performed by ICC !! "));

                                    break;
                                }
                            case "000100":
                                if (Terminal_Config == "ON" || Terminal_Config == "NF")
                                {
                                    emvList.add(createEmvLog(">>> Enciphered PIN verification will be performed by ICC !!  "));
                                    break;
                                }
                            case "000101":
                                if (Terminal_Config == "OF" && pCondition == "03")
                                {
                                    emvList.add(createEmvLog(">>> Enciphered PIN verification performed by ICC and signature (paper) !! "));
                                    break;
                                }
                            case "000010":
                                if ((Terminal_Config == "ON" || Terminal_Config == "NF") && pCondition == "03")
                                {
                                    emvList.add(createEmvLog(">>> Enciphered PIN verified online !!"));
                                    break;
                                }


                            case "011110":

                                emvList.add(createEmvLog(">>> Signature (paper) !!"));
                                break;
                            case "011111":
                                emvList.add(createEmvLog(">>> No CVM required !!"));
                                break;
                            default:
                                break;
                        }
                    }


                }




            }

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Read terminalRiskManagement  successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in Read terminalRiskManagement, please check with your provider !", null);
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
