package com.simulator.atm.services.atmm;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.atm.repositories.repoatm.NDCStateTypeRepository;
import com.simulator.config.GlobalVars;
import com.simulator.entities.NDCStateType;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NDCStateTypeService {
    @Autowired
    NDCStateTypeRepository ndcStateTypeRepository;
    private JSONObject joResp = new JSONObject();

    private final Logger logger = LogManager.getLogger(LogNDCController.class);


    public ResponseApiJson<List<NDCStateType>> getNDCType() {
        String idRequest = "NDC_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<NDCStateType> data = ndcStateTypeRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting NDC state type successful", data);

        } catch (Exception ex) {
            logger.error("Exception in getNDCType: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getNDCType, please check with your provider !");
        }
    }

}
