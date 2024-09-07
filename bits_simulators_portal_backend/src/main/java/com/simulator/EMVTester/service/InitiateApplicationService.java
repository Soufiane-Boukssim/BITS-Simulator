package com.simulator.EMVTester.service;


import com.simulator.EMVTester.dto.Emv;
import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InitiateApplicationService {

    private final Logger logger = LogManager.getLogger(LogNDCController.class);



    public ResponseApiJson<List<Emv>> InitiateApplication() {
        String idRequest = "Initiate_Application_" + new Date().getTime() + (Math.random() * 9999);
        List<Emv> emvList = new ArrayList<>();

        try {
            emvList.add(createEmvLog("Resetting TVR and TSI..."));
            emvList.add(createEmvLog("List Reader Name is [Broadcom Corp Contacted SmartCard 0]"));
            emvList.add(createEmvLog("List Reader Name is [Gemplus USB SmartCard Reader 0]"));
            emvList.add(createEmvLog("Number Reader : 2 Reader Name is [Gemplus USB SmartCard Reader 0]"));
            emvList.add(createEmvLog("Build Application in progress ..."));
            emvList.add(createEmvLog("[1PAY.SYS.DDF01] Exist "));
            emvList.add(createEmvLog("Application [A0 00 00 00 03 20 10 ] Found ... "));
            emvList.add(createEmvLog("ATR is [3B 78 96 00 00 81 00 03 50 01 07 90 00 ]"));

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "InitiateApplication profile successful", emvList);
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in InitiateApplication, please check with your provider !", null);
        }
    }

        private Emv createEmvLog(String description) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return new Emv(sdf.format(new Date()), description);
        }


    private static void InitTVR_CVR_TSI() {
        System.out.println("Resetting TVR and TSI...");

    }

    private static void InitApplicationData() {
        System.out.println("Initializing Application Data...");

    }

    private static void GetReader() {
        try {
            System.out.println("Get Smart Card Reader Name...");
            // Votre logique ici
        } catch (Exception e) {
            System.out.println("Error in getting Smart Card Reader: " + e.getMessage());
        }
    }

    private static void BuildApplication() {
        try {
            System.out.println("Building Application...");
            // Votre logique ici
        } catch (Exception e) {
            System.out.println("Error in building application: " + e.getMessage());
            throw e;
        }
    }

}
