package com.simulator.smartICC.services;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.TestCases;
import com.simulator.smartICC.repositories.TestCaseRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TestCaseService {


    @Autowired
    private TestCaseRepository testCaseRepository;

    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;



    public ResponseApiJson<List<TestCases>> getTestCasesByCodeProfile(String codeProfile) {
        String idRequest = "GET_TAG_DEFINITION_" + System.currentTimeMillis();
//        logger.info("---->codeProfile:"+codeProfile);
        try {
            List<TestCases> tagDefinitionOptional = testCaseRepository.findByProfileCode(codeProfile);
//            logger.info("---->tagDefinitionOptional.isPresent:"+tagDefinitionOptional);
            if (tagDefinitionOptional.size()>0) {

                return new ResponseApiJson<List<TestCases>>(idRequest, GlobalVars.SUCCESS, "Test case found successfully", tagDefinitionOptional);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Test Case not found for code profile: " + codeProfile, null);
            }
        } catch (Exception ex) {
//            logger.error("Exception in getTagDefinitionByCodeProfile: " + ex.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues occurred while retrieving Test Case, please check with your provider !");
        }
    }


    public ResponseApiJson<List<TestCases>> saveTestCases(List<TestCases> listCase) {
        String idRequest = "INSERT_TEST_CASE" + System.currentTimeMillis();
//        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            List<TestCases> insertedCases = testCaseRepository.saveAll(listCase);
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Tag Definition", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert Tag Definition", "Tag Definition inserted successfully", new Date());
//            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserting Cases successful", insertedCases);
        } catch (Exception ex) {
//            logger.error("Exception in saveTagDefinition: " + ex.getMessage());
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "Tag Definition", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert Tag Definition", "An error occurred while inserting Tag Definition", new Date());
//            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues occurred while inserting Tag Definition, please check with your provider!");
        }
    }




    public ResponseApiJson<String> deleteCasesProfile(String code_profil) {
        String idRequest = "DELETE_CPA_PROFILE_" + new Date().getTime() + (Math.random() * 9999);
//        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());

        try {
            testCaseRepository.deleteByProfileCode(code_profil);
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "EMV profile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Delete CPA profile", "CPA profile deleted successfully", new Date());
//            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleting CPA profile successful", code_profil);
        } catch (Exception ex) {
//            logger.error("Exception in deleteProfile: " + ex.getMessage());
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "CPA profile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete CPA profile", "An error occurred while deleting CPA profile", new Date());
//            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in deleteProfile, please check with your provider !");
        }
    }




    public ResponseApiJson<List<Map<String, Object>>> getGroupedTestCasesByCodeProfile(String codeProfile) {
        String idRequest = "GET_TAG_DEFINITION_" + System.currentTimeMillis();
        try {
            List<TestCases> testCases = testCaseRepository.findByProfileCode(codeProfile);
            if (!testCases.isEmpty()) {
                // Créer une carte pour stocker les cas de test groupés par wording
                Map<String, Map<String, Object>> groupedTestCases = new HashMap<>();

                for (TestCases testCase : testCases) {
                    // Vérifier si le wording existe déjà dans la carte
                    if (groupedTestCases.containsKey(testCase.getWording())) {
                        // Ajouter les détails du cas de test au tableau global
                        Map<String, Object> globalTestCase = new HashMap<>();
                        globalTestCase.put("sub_case", testCase.getSub_case());
                        globalTestCase.put("description", testCase.getDescription());
                        globalTestCase.put("exec_seq", testCase.getExec_seq());
                        ((List<Map<String, Object>>) groupedTestCases.get(testCase.getWording()).get("global")).add(globalTestCase);
                    } else {
                        // Créer une nouvelle entrée pour le wording
                        Map<String, Object> newTestCaseEntry = new HashMap<>();
                        newTestCaseEntry.put("id", testCase.getId());
                        newTestCaseEntry.put("profileCode", testCase.getProfileCode());
                        newTestCaseEntry.put("test_case", testCase.getTest_case());
                        newTestCaseEntry.put("wording", testCase.getWording());

                        // Créer une liste pour le tableau global et ajouter le premier cas de test
                        List<Map<String, Object>> globalTestCases = new ArrayList<>();
                        Map<String, Object> globalTestCase = new HashMap<>();
                        globalTestCase.put("sub_case", testCase.getSub_case());
                        globalTestCase.put("description", testCase.getDescription());
                        globalTestCase.put("exec_seq", testCase.getExec_seq());
                        globalTestCases.add(globalTestCase);

                        // Ajouter le tableau global à l'entrée du cas de test
                        newTestCaseEntry.put("global", globalTestCases);

                        // Ajouter l'entrée du cas de test à la carte groupée
                        groupedTestCases.put(testCase.getWording(), newTestCaseEntry);
                    }
                }

                // Convertir la carte en une liste pour le résultat de réponse
                List<Map<String, Object>> result = new ArrayList<>(groupedTestCases.values());
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Test cases found successfully", result);
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "Test Case not found for code profile: " + codeProfile, null);
            }
        } catch (Exception ex) {
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues occurred while retrieving Test Case, please check with your provider !");
        }
    }


}
