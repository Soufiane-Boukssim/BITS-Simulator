package com.simulator.atm.controllers.controlleratm;

import com.simulator.atm.services.atmm.TerminalLogService;
import com.simulator.entities.Protocole;
import com.simulator.entities.TerminalLog;
import com.simulator.entities.UserHabilitationParam;
import com.simulator.globalModels.ResponseApiJson;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/atm/terminal_log")
public class TerminalLogController {
    @Autowired
    TerminalLogService terminalLogService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/logs")
    private ResponseApiJson<List<TerminalLog>> getLogs(@RequestBody Map<String, String> requestBody) {
        String bankCode = requestBody.get("bankCode");
        String dateFromStr = requestBody.get("dateFromStr");
        String dateToStr = requestBody.get("dateToStr");
        ResponseApiJson<List<TerminalLog>> responseApiJson = terminalLogService.getTerminalLogs(bankCode ,dateFromStr ,dateToStr);
        return responseApiJson;
    }

    @PostMapping("/saveMessages")
    public ResponseApiJson<?> saveMessages(@RequestBody Map<String, String> requestBody) {

            String messageInData = requestBody.get("messageIn");
            String messageOutData = requestBody.get("messageOut");
         //   terminalLogService.saveMessages(messageInData, messageOutData);

            ResponseApiJson<?> responseApiJson =  terminalLogService.saveMessages(messageInData, messageOutData);
            return responseApiJson;

//            return ResponseEntity.ok(new ResponseApiJson<>("", "000", "Messages saved successfully", ""));

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/user_role_page")
    private ResponseApiJson<List<UserHabilitationParam>> getUserRolePage(@RequestBody Map<String, String> requestBody) {
       System.out.println("-----> getUserRolePage ");
        String user_code = requestBody.get("user_code");
        System.out.println("-----> getUserRolePage user_code : " +user_code);
        ResponseApiJson<List<UserHabilitationParam>> responseApiJson = terminalLogService.getUserRolePage(user_code);
        return responseApiJson;
    }


    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/protocoles")
    private ResponseApiJson<List<Protocole>> getAllProtocoll() {
        System.out.println("-----> getAllProtocoll ");
        ResponseApiJson<List<Protocole>> responseApiJson = terminalLogService.getAllProtocol();
        return responseApiJson;
    }



}
