package com.simulator.globalController;

import com.simulator.entities.Bank;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.globalService.BankService;
import com.simulator.models.RequestId;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sa/bank")
@RequiredArgsConstructor
public class BankController {
    private final Logger logger = LogManager.getLogger(BankController.class);
    @Autowired
    BankService bankService;

    @PostMapping("/getAllBanks")
    public ResponseApiJson<List<Bank>> getAllBanks() {
        logger.info("###################Start getAllBanks #####################");
        ResponseApiJson<List<Bank>> responseApiJson = bankService.getAllBanks();
        logger.info("###################End getAllBanks #####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/getOneBanks")
    public ResponseApiJson<List<Bank>> getOneBank(@RequestBody RequestId id) {
        logger.info("###################Start getOneBank #####################");
        ResponseApiJson<List<Bank>> responseApiJson = bankService.getOneBank(id.getId());
        logger.info("###################End getOneBank #####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/addBank")
    public ResponseApiJson<?> addBank(@RequestBody Bank bank) {
        logger.info("###################Start addBank #####################");
        ResponseApiJson<?> responseApiJson = bankService.addBank(bank);
        logger.info("###################End addBank #####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/updateBank")
    public ResponseApiJson<?> updateBank(@RequestBody Bank bank) throws IllegalAccessException {
        logger.info("###################Start updateBank #####################");
        ResponseApiJson<?> responseApiJson = bankService.updateBank(bank);
        logger.info("###################End updateBank #####################");
        return responseApiJson;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/deleteBank")
    public ResponseApiJson<?> deleteBank(@RequestBody RequestId id) {
        logger.info("###################Start deleteBank #####################");
        ResponseApiJson<?> responseApiJson = bankService.getOneBank(id.getId());
        logger.info("###################End deleteBank #####################");
        return responseApiJson;
    }
}
