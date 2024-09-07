package com.simulator.pos.controllers;


import com.simulator.config.GlobalVars;
import com.simulator.entities.pos.PosCaseSetInfo;
import com.simulator.entities.pos.PosCasesDefinition;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.pos.model.PosDataToShow;
import com.simulator.pos.model.PosResponseModel;
import com.simulator.pos.services.PosWebSeeviceIsoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/su/pos/webService")
@RequiredArgsConstructor
public class PosWebServiceController {
    private final Logger logger = LogManager.getLogger(PosWebServiceController.class);
    @Autowired
    PosWebSeeviceIsoService webSeeviceIsoService;

    @Autowired
    GlobalVars globalVars;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getBuffer")
    public ResponseApiJson<?> getBuffer(@RequestBody PosCaseSetInfo caseSetInfo , @RequestParam String protocolCode) {
        logger.info("#################### Star getBuffer ####################");

        String msg="ISO71000000";

        ResponseApiJson<?> responseApiJson;
        PosCasesDefinition casesDefinition= new PosCasesDefinition();
        for (PosCasesDefinition definition: caseSetInfo.getPosCasesDefinitions()){
            if(definition.getCaseDirection().equals("req")){
                casesDefinition=definition;
                logger.info("protocolCode test ====>"+protocolCode+" "+definition);
            }
        }
        responseApiJson = webSeeviceIsoService.getBuffer(casesDefinition, protocolCode,msg);
        return responseApiJson;
    }

    /*@SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getBuffer")
    public ResponseApiJson<?> getBuffer(@RequestBody CasesDefinition casesDefinition , @RequestParam String protocolCode) {
        logger.info("#################### Star getBuffer ####################");
        logger.info("protocolCode test ====>"+protocolCode+" "+casesDefinition);
        String msg="ISO71000000";

        ResponseApiJson<?> responseApiJson;
        responseApiJson = webSeeviceIsoService.getBuffer(casesDefinition, protocolCode,msg);
        return responseApiJson;
    }*/

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/responseMessage")
    public ResponseApiJson<?> responseMessage(@RequestBody PosResponseModel s) {
        logger.info("#################### Star responseMessage #################### "+s.toString());
        ResponseApiJson<?> responseApiJson;
        String protocolCode="SS";
        String msg="ISO71000000";
        responseApiJson = webSeeviceIsoService.responseMessage(s,protocolCode,msg);
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getDataToShow")
    public ResponseApiJson<?> getDataTable(@RequestBody PosDataToShow dataToShow, @RequestParam String protocolCode,
                                           @RequestParam String bankCode) {
        logger.info("#################### Star getDataToShow ####################");
        logger.info("protocolCode test ====>"+protocolCode);
        logger.info("bankCode test ====>"+bankCode);
        ResponseApiJson<?> responseApiJson;
        responseApiJson = webSeeviceIsoService.getDataShow(dataToShow,protocolCode,bankCode);
        return responseApiJson;
    }




    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getMsgKeyExchange")
    public ResponseApiJson<?> getMsgKeyExchange(@RequestBody Map<String, String> requestData) {
        logger.info("#################### Star getMsgKeyExchange ####################");
        String data = requestData.get("data");
        String protocolCode = requestData.get("protocolCode");


        ResponseApiJson<?> responseApiJson;
        responseApiJson = webSeeviceIsoService.getMsgKeyExchange(data,protocolCode);
        return responseApiJson;
    }






    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/SidDump")
    public ResponseApiJson<?> getSidDump(@RequestBody Map<String, String> data) {
        logger.info("#################### Star SidDump ####################");
        String messageIn = data.get("messageIn");
        String protocolCode = data.get("protocolCode");

        ResponseApiJson<?> responseApiJson;
        responseApiJson = webSeeviceIsoService.getSidDump(messageIn,protocolCode);
        return responseApiJson;
    }


    @PostMapping("/SidMsgHexTrace")
    public ResponseApiJson<?> SidMsgHexTrace(@RequestBody Map<String, String> data) {
        logger.info("#################### Star SidMsgHexTrace ####################");
        String messageIn = data.get("messageIn");
        String protocolCode = data.get("protocolCode");

        ResponseApiJson<?> responseApiJson;
        responseApiJson = webSeeviceIsoService.SidMsgHexTrace(messageIn,protocolCode);
        return responseApiJson;
    }

    @PostMapping("/clear_Session")
    public ResponseApiJson<?> clearSession() {
        logger.info("#################### Star clearSession ####################");

        ResponseApiJson<?> responseApiJson;
        responseApiJson = webSeeviceIsoService.clearSession();
        return responseApiJson;
    }
}




