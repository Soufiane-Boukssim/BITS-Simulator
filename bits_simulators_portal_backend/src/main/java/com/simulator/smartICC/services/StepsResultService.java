package com.simulator.smartICC.services;


import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.StepsParams;
import com.simulator.smartICC.models.StepsResult;
import com.simulator.smartICC.repositories.StepsParamsRepository;
import com.simulator.smartICC.repositories.StepsResultRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StepsResultService {


    @Autowired
    private StepsResultRepository stepsResultRepository;

    @Autowired
    private StepsParamsRepository stepsParamsRepository;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;



    public ResponseApiJson<List<Map<String, Object>>> getTestResultByCodeProfile(String codeProfile) {
        String idRequest = "GET_RESULT_TEST" + System.currentTimeMillis();
        try {
            List<StepsResult> stepsResults = stepsResultRepository.findByProfileCode(codeProfile);
            if (!stepsResults.isEmpty()) {
                List<Map<String, Object>> result = new ArrayList<>();

                for (StepsResult stepsResult : stepsResults) {
                    Map<String, Object> resultMap = new HashMap<>();

                    // Ajoutez les données de StepsResult à la carte de résultat
                    resultMap.put("profile_code", stepsResult.getProfileCode());
                    resultMap.put("RESULT", stepsResult.getRESULT());
                    // Ajoutez les autres champs de StepsResult ici...

                    // Recherchez le libellé dans StepsParams
                    Optional<StepsParams> stepsParamsOptional = stepsParamsRepository.findById(stepsResult.getSTEP_CODE());
                    if (stepsParamsOptional.isPresent()) {
                        StepsParams stepsParams = stepsParamsOptional.get();
                        // Ajoutez le libellé à la carte de résultat
                        resultMap.put("WORDING", stepsParams.getWORDING());
                    } else {
                        // Si aucun libellé n'est trouvé, mettez une valeur par défaut ou ignorez
                        resultMap.put("WORDING", "N/A");
                    }

                    result.add(resultMap);
                }

                return new ResponseApiJson<List<Map<String, Object>>>(idRequest, GlobalVars.SUCCESS, "Test cases found successfully", result);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Test Case not found for code profile: " + codeProfile, null);
            }
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues occurred while retrieving Test Case, please check with your provider !");
        }
    }





}
