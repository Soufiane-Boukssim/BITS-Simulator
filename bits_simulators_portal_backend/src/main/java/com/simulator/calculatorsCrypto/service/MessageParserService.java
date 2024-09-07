package com.simulator.calculatorsCrypto.service;

import com.simulator.calculatorsCrypto.model.ResultData;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.FieldsRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class MessageParserService {
    private final Logger logger = LogManager.getLogger(MessageParserService.class);


    @Autowired
    private FieldsRepository fieldsRepository;



//    public ResponseApiJson<?> parseData(String data , FieldsDefinitionId id) {
//        String idRequest = "Msg_Parser" + new Date().getTime() + (Math.random() * 9999);
//        try {
////            List<FieldsDefinition> fieldsDefinitions = fieldsRepository.findByBankCodeAndFieldsProtocole(id.getBankCode(),id.getFieldProtocole());
////            logger.info("fieldsDefinitions ["+fieldsDefinitions+"]");
//            logger.info("parse() data=["+data+"]");
//
//            // Convertir la chaîne hexadécimale en ASCII
//            StringBuilder asciiBuilder = new StringBuilder();
//            for (int i = 0; i < data.length(); i += 2) {
//                String hex = data.substring(i, i + 2);
//                int decimal = Integer.parseInt(hex, 16);
//                asciiBuilder.append((char) decimal);
//            }
//            String asciiString = asciiBuilder.toString();
//
//            // Supprimer les caractères non imprimables de la chaîne ASCII résultante
//            asciiString = asciiString.replaceAll("[^\\p{Print}]", "");
//
//            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "data parcer", asciiString);
//
//        } catch (Exception e) {
//            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "error de data parcer", "");
//        }
//    }



    public ResponseApiJson<?> parseData(String data) {
        String idRequest = "Msg_Parser" + new Date().getTime() + (Math.random() * 9999);
        try {
            logger.info("parse() data=[" + data + "]");

            // Convertir la chaîne hexadécimale en ASCII
            StringBuilder asciiBuilder = new StringBuilder();
            for (int i = 0; i < data.length(); i += 2) {
                String hex = data.substring(i, i + 2);
                int decimal = Integer.parseInt(hex, 16);
                asciiBuilder.append((char) decimal);
            }
            String asciiString = asciiBuilder.toString();

            // Supprimer les caractères non imprimables de la chaîne ASCII résultante
            asciiString = asciiString.replaceAll("[^\\p{Print}]", "");

            // Créer une liste pour stocker les résultats
            List<ResultData> resultList = new ArrayList<>();

            // Extraire les sous-chaînes aux positions spécifiées
            resultList.add(new ResultData(0, asciiString.substring(0, 4)));
            resultList.add(new ResultData(7, asciiString.substring(6, 15)));
            resultList.add(new ResultData(11, asciiString.substring(16, 21)));
            resultList.add(new ResultData(12, asciiString.substring(22, 33)));
            resultList.add(new ResultData(24, asciiString.substring(34, 36)));
            resultList.add(new ResultData(25, asciiString.substring(37, 41)));
            resultList.add(new ResultData(33, asciiString.substring(43, 48)));
            resultList.add(new ResultData(37, asciiString.substring(49, 60)));

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "data parcer", resultList);

        } catch (Exception e) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "error de data parcer", null);
        }
    }




}
