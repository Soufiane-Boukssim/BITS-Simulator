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
import com.simulator.smartICC.models.EMVProfile;
import com.simulator.smartICC.models.IssuerConfig;
import com.simulator.smartICC.repositories.EMVProfileRepository;
import com.simulator.smartICC.repositories.IssuerConfigRepository;
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
public class CompletionService {
    @Autowired
    private EMVProfileRepository profileRepository;

    @Autowired
    private TransactionConfigRepository transactionConfigRepository;


    @Autowired
    private TerminalConfigRepository terminalConfigRepository;

    @Autowired
    private IssuerConfigRepository issuerConfigRepository;


    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    GlobalVars globalVars;
    @Autowired
    private HistoryPAraREpository historyParaRepository;

    public ResponseApiJson<List<Emv>> completion(Object body) {
        String idRequest = "Completion" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();
//        logger.info("---->body");
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            emvList.add(createEmvLog("Issuer Response in Progress ..."));


            // Conversion du corps en objet JsonNode
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.convertValue(body, JsonNode.class);



             Boolean _Declined = false;
             Boolean _Able_To_GoOnline = true;
             Boolean _IssAccepted = true;
             Boolean _SendAutheticationData = true;
             String _PaymentKey="";
            Boolean _IAuthSupported=false;
            List<String> tagList=new ArrayList<>() ;


            Optional<HistoryPara>  historyParaOptional= historyParaRepository.findById(userM.get().getUserCode());
            if (historyParaOptional.isPresent()) {
                HistoryPara  historyPara=historyParaOptional.get();
                _Declined=historyPara.get_Declined();
                _Able_To_GoOnline=historyPara.get_Able_To_GoOnline();
                _IssAccepted=historyPara.get_IssAccepted();
                _SendAutheticationData=historyPara.get_SendAutheticationData();
                _IAuthSupported=historyPara.get_IAuthSupported();
            }

            // Fonction pour trouver la valeur correspondante à "Name": "9F38"
            String tag9F27Value = findTagValue(rootNode, "9F27");
            String tag5AValue = findTagValue(rootNode, "5A");
            String tag5F34Value = findTagValue(rootNode, "5F34");
            String tag82Value = findTagValue(rootNode, "82");
            String tag9F36Value = findTagValue(rootNode, "9F36");
            String tag9F10Value = findTagValue(rootNode, "9F10");

            String tag8DValue = findTagValue(rootNode, "8D");
            String _ARC="";
            if (_Declined)
            {
                emvList.add(createEmvLog("Authorisation Refused By Terminal"));
            } else if (_Able_To_GoOnline)
            {
                emvList.add(createEmvLog("Send Authorisation To Issueur"));

                emvList.add(createEmvLog("Application PAN [" + tag5AValue + "]"));
                emvList.add(createEmvLog("Application PAN Sequence Number [" + tag5AValue + "]"));

                Optional<EMVProfile> optionalActiveProfile = profileRepository.findByActiveProfile("Y");
                if (optionalActiveProfile.isPresent()) {
                    EMVProfile activeProfile = optionalActiveProfile.get();
                    String codeProfile = activeProfile.getCodeProfile();
                    logger.info("code profile activeProfile " + codeProfile);

                    Optional<IssuerConfig> issuerConfigOptional  = issuerConfigRepository.findByCodeProfile(codeProfile);
                    if (issuerConfigOptional.isPresent()) {
                        IssuerConfig issuerConfig = issuerConfigOptional.get();
                        _PaymentKey = issuerConfig.getPaymentKey();

                    } else {
                        // Aucune configuration de transaction trouvée pour ce code de profil
                        logger.info("Aucune configuration de transaction trouvée pour le code de profil: " + codeProfile);
                    }
                }

                emvList.add(createEmvLog("Master Payment Key [" + _PaymentKey + "]"));
                 if (_IssAccepted){
                     _ARC="3030";
                     emvList.add(createEmvLog("Authorisation Response Code [" + 3030 + "]"));
                 }else {
                     _ARC="3035";
                     emvList.add(createEmvLog("Master Payment Key [" + 3035 + "]"));
                 }
            }
            else {
                emvList.add(createEmvLog("Terminal can't Go Online"));
            }
            emvList.add(createEmvLog(" Generate ARPC For Application [" + "VIS" + "] ARQC [" + "" + "]Card [" + tag5AValue + "]Seq [" + tag5F34Value + "]Master Key [" + _PaymentKey + "]ARC [" + _ARC + "]"));


             //-------------------------------------------


            emvList.add(createEmvLog("Completion in Progress ..."));

            tagList = getCDOLList(tag8DValue);
            for (String item : tagList) {
                // Utilisation des éléments de la liste de TAGs selon vos besoins
                emvList.add(createEmvLog("Tag: " + item+"  value : "));
            }
            logger.info("-->tagList"+tagList);



            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Completion  successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in  Completion, please check with your provider !", null);
        }
    }


    private List<String> getCDOLList(String pCDOL) {
        List<String> tagList = new ArrayList<>();
        int i = 0;
        int num = 0;
        try {
            for (; i < pCDOL.length(); i += num + 2) {
                num = getLenTagName(pCDOL.substring(i));
                tagList.add(pCDOL.substring(i, i + num));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tagList;
    }

    // Méthode pour obtenir la longueur du nom du TAG
    private int getLenTagName(String tagName) {
        try {
            if ((Integer.parseInt(hexaToDec(tagName.substring(0, 2)), 16) & 0x1F) == 31) {
                return 4;
            }
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // Méthode utilitaire pour convertir de l'héxadécimal en décimal
    private String hexaToDec(String hex) {
//        return Integer.toString(Integer.parseInt(hex, 16));
        return Bin_To_Dec(Hexa_To_Bin(hex));
    }


    private String Bin_To_Dec(String pBin) {
        long num = 0L;
        if (isBin(pBin)) {
            for (int num2 = pBin.length(); num2 > 0; num2--) {
                num += Long.parseLong(pBin.substring(num2 - 1, num2)) * Math.pow(2.0, pBin.length() - num2);
            }
        }
        return Long.toString(num);
    }
    private boolean isBin(String pBin) {
        boolean flag = true;
        if (pBin.length() == 0) {
            return false;
        }

        for (int i = 0; i < pBin.length(); i++) {
            char c = pBin.charAt(i);
            switch (Character.toUpperCase(c)) {
                case '0':
                case '1':
                    flag = true;
                    break;
                default:
                    flag = false;
                    break;
            }

            if (!flag) {
                return flag;
            }
        }

        return flag;
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

