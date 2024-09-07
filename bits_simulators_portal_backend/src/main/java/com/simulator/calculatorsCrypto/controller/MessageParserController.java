package com.simulator.calculatorsCrypto.controller;

import com.simulator.calculatorsCrypto.service.MessageParserService;
import com.simulator.globalModels.ResponseApiJson;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/su/tools/messageParser")
@RequiredArgsConstructor
public class MessageParserController {

    private final Logger logger = LogManager.getLogger(MessageParserController.class);

    @Autowired
    private MessageParserService messageParserService;


//    List<TagEMV> recordList = new ArrayList<TagEMV>();

    @PostMapping("/parceData")
    public ResponseApiJson<?> parceData(@RequestBody Map<String, String> requestData) {
        logger.info("#################### Start parceData ####################");

        String data = requestData.get("data");
//        String fieldProtocole = requestData.get("fieldProtocole");
//        String bankCode = requestData.get("bankCode");
//
//        // Create a FieldsDefinitionId object
//        FieldsDefinitionId id = new FieldsDefinitionId();
//        id.setFieldProtocole(fieldProtocole);
//        id.setBankCode(bankCode);

        // Pass the created FieldsDefinitionId object to parseData
        ResponseApiJson<?> responseApiJson = messageParserService.parseData(data);
        return responseApiJson;
    }

}
