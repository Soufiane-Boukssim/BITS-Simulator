package com.simulator.EMVTester.controller;

import com.simulator.EMVTester.dto.Emv;
import com.simulator.EMVTester.service.*;
import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.globalModels.ResponseApiJson;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/InitiateApplication")
@SecurityRequirement(name = "Bearer Authentication")
public class InitiateApplicationController {
    @Autowired
    private InitiateApplicationService initiateApplicationService;

    @Autowired
    private ReadApplicationDataService readApplicationDataService;

    @Autowired
    private OfflineDataAuthenticationService offlineDataAuthenticationService;


    @Autowired
    private ProcessingRestrictionService processingRestrictionService;
    @Autowired
    private CardHolderVerificationService cardHolderVerificationService;

    @Autowired
    private TerminalRiskManagementService terminalRiskManagementService;

    @Autowired
    private TerminalActionAnalysisService terminalActionAnalysisService;

    @Autowired
    private CardActionAnalysisService cardActionAnalysisService;
    @Autowired
    private AuthorisationRequestService authorisationRequestService;

    @Autowired
    private IssuerResponseService issuerResponseService;
    @Autowired
    private CompletionService completionService;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);

    private Object requestBody;

    @PostMapping("/getInitiateApplication")
    private ResponseApiJson<List<Emv>> getInitiateApplication(){
        try {
            ResponseApiJson<List<Emv>> responseApiJson = initiateApplicationService.InitiateApplication();
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred InitiateApplicationService", null);
        }
    }

    @PostMapping("/getReadApplicationData")
    private ResponseApiJson<List<Emv>> getReadApplicationData(@RequestBody Object requestBody) {
        try {
            logger.info("---->body V1"+requestBody);
            this.requestBody = requestBody;
            ResponseApiJson<List<Emv>> responseApiJson = readApplicationDataService.ReadApplicationData(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred ReadApplicationData", null);
        }
    }

    @PostMapping("/getOfflineDataAuthentication")
    private ResponseApiJson<List<Emv>> getOfflineDataAuthentication(@RequestBody Object requestBody) {
        try {
            // Object requestBody= this.requestBody;
//            logger.info("---->body "+body);
            ResponseApiJson<List<Emv>> responseApiJson = offlineDataAuthenticationService.offlineDataAuthentication(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred offline Data Authentication", null);
        }
    }

    @PostMapping("/getProcessingRestriction")
    private ResponseApiJson<List<Emv>> getProcessingRestriction(@RequestBody Object requestBody) {
        try {
//            logger.info("---->body V1"+requestBody);
            ResponseApiJson<List<Emv>> responseApiJson = processingRestrictionService.processingRestriction(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred offline Data Authentication", null);
        }
    }


    @PostMapping("/getCardHolderVerification")
    private ResponseApiJson<List<Emv>> getCardHolderVerification(@RequestBody Object requestBody) {
        try {
//            logger.info("---->body V1"+requestBody);
            ResponseApiJson<List<Emv>> responseApiJson = cardHolderVerificationService.cardHolderVerification(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred offline Data Authentication", null);
        }
    }


    @PostMapping("/getTerminalRiskManagement")
    private ResponseApiJson<List<Emv>> getTerminalRiskManagement(@RequestBody Object requestBody) {
        try {
//            logger.info("---->body V1"+requestBody);
            ResponseApiJson<List<Emv>> responseApiJson = terminalRiskManagementService.terminalRiskManagement(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred offline Data Authentication", null);
        }
    }



    @PostMapping("/getTerminalActionAnalysis")
    private ResponseApiJson<List<Emv>> getTerminalActionAnalysis(@RequestBody Object requestBody) {
        try {
//            logger.info("---->body V1"+requestBody);
            ResponseApiJson<List<Emv>> responseApiJson = terminalActionAnalysisService.terminalActionAnalysis(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred offline Data Authentication", null);
        }
    }


    @PostMapping("/getCardActionAnalysis")
    private ResponseApiJson<List<Emv>> getCardActionAnalysis(@RequestBody Object requestBody) {
        try {
//            logger.info("---->body V1"+requestBody);
            ResponseApiJson<List<Emv>> responseApiJson = cardActionAnalysisService.cardActionAnalysis(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred offline Data Authentication", null);
        }
    }

    @PostMapping("/getAuthorisationRequest")
    private ResponseApiJson<List<Emv>> getAuthorisationRequest(@RequestBody Object requestBody) {
        try {
//            logger.info("---->body V1"+requestBody);
            ResponseApiJson<List<Emv>> responseApiJson = authorisationRequestService.AuthorisationRequest(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred offline Data Authentication", null);
        }
    }

    @PostMapping("/getIssuerResponse")
    private ResponseApiJson<List<Emv>> getIssuerResponse(@RequestBody Object requestBody) {
        try {
//            logger.info("---->body V1"+requestBody);
            ResponseApiJson<List<Emv>> responseApiJson = issuerResponseService.issuerResponse(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred offline Data Authentication", null);
        }
    }


    @PostMapping("/getCompletion")
    private ResponseApiJson<List<Emv>> getCompletion(@RequestBody Object requestBody) {
        try {
//            logger.info("---->body V1"+requestBody);
            ResponseApiJson<List<Emv>> responseApiJson = completionService.completion(requestBody);
            return responseApiJson;
        } catch (Exception ex) {
            return new ResponseApiJson<>("error", "An error occurred offline Data Authentication", null);
        }
    }




}
