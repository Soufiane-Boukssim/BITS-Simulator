package com.simulator.atm.services.atmm;//package com.simulator.atm.services.atmm;
//
//import com.simulator.atm.controllers.controlleratm.AtmCardProfileController;
//import com.simulator.config.GlobalVars;
//import com.simulator.entities.CardProfile;
//import com.simulator.entities.CardProfileId;
//import com.simulator.entities.UserActivityTracking;
//import com.simulator.entities.UserManagement;
//import com.simulator.globalModels.ResponseApiJson;
//import com.simulator.repository.CardRepository;
//import com.simulator.repository.UserActivityTrackingRepository;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//
//@Service
//public  class AtmCardProfileService {
//
//
//    private final CardRepository cardRepository;
//
//    private final Logger logger = LogManager.getLogger(AtmCardProfileController.class);
//
//
//    public AtmCardProfileService(CardRepository cardRepository) {
//        this.cardRepository = cardRepository;
//    }
//
//    @Autowired
//    UserActivityTrackingRepository userActivityTrackingRepository;
//
//    @Autowired
//    GlobalVars globalVars;
///*
//    public <CardProfile> List<CardProfile> getAllCardProfiles() {
//        return (List<CardProfile>) cardRepository.findAll();
//    }
//*/
//
//
//    public Optional<CardProfile> getCardProfileById(CardProfileId cardProfileId) {
//        return cardRepository.findById(cardProfileId);
//    }
//
//
//   /* public ResponseApiJson<?> addCardProfile(CardProfile cardProfile) {
//        cardRepository.save(cardProfile);
//        return null;
//    }*/
//
//
//    public ResponseApiJson<List<CardProfile>> getAllCardProfiles() {
//        String idRequest = "CPS_" + new Date().getTime() + (Math.random() * 9999);
//        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
//        try {
//            List<CardProfile> cardProfiles = cardRepository.findAll();
//
//            UserActivityTracking userTrace = new UserActivityTracking(userM.isPresent() ? userM.get().getUserCode() : null,
//                    "Retrieve", "AtmCard", "000.000.00.00", "Success", GlobalVars.SUCCESS,
//                    "Retrieve AtmCard", "AtmCard retrieved successfully", new Date());
//
//            userActivityTrackingRepository.save(userTrace);
//
//            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all atmcard succesfully  !", cardProfiles);
//
//        } catch (Exception e) {
//            logger.info("Exception of this::getAllCardProfiles " + e.getMessage());
//            UserActivityTracking userTrace = new UserActivityTracking(userM.isPresent() ? userM.get().getUserCode() : null,
//                    "Retrieve", "AtmCard", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE,
//                    "Retrieve AtmCard", "some issues in getAllCardProfiles please check with your provider !", new Date());
//            userActivityTrackingRepository.save(userTrace);
//
//            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCardProfiles please check with your provider !");
//
//        }
//    }
//
//    public ResponseApiJson<?> addCardProfile(CardProfile cardProfile) {
//        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
//        logger.info(cardProfile.toString());
//        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
//        try {
//            Optional<CardProfile> cardProfiletoCheck = cardRepository.findById(cardProfile.getId());
//            if (cardProfiletoCheck.isEmpty()) {
//                cardRepository.save(cardProfile);
//
//                // Track user activity
//                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "atmcardProfile", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Insert atmcardProfile", "atmcardProfile inserted successfully", new Date());
//                userActivityTrackingRepository.save(userTrace);
//
//                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde atmcardProfile sucessfully  !");
//            } else {
//                // Track user activity
//                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Insert", "atmcardProfile", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Insert atmcardProfile", "this atmCardProfile already exist !", new Date());
//                userActivityTrackingRepository.save(userTrace);
//
//                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this atmCardProfile already exist !");
//
//            }
//        } catch (Exception e) {
//            logger.info("Exception of getOneAtmCardProfile " + e.getMessage());
//
//            // Track user activity for exception
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Insert", "atmcardProfile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Insert atmcardProfile", " Some issues in addAtmCardProfile please check with your provider !", new Date());
//            userActivityTrackingRepository.save(userTrace);
//
//
//            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addAtmCardProfile please check with your provider !");
//        }
//
//    }
//
//
//        public ResponseApiJson<List<CardProfile>> getOneCardProfile(CardProfileId id) {
//            String idRequest = "CPSS_" + new Date().getTime() + (Math.random() * 9999);
//            try {
//                Optional<CardProfile> cardProfile = cardRepository.findById(id);
//                List<CardProfile> cardProfiles = new ArrayList<>();
//                cardProfile.ifPresent(cardProfiles::add);
//                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one AtmCardProfile succesfully  !" , cardProfiles);
//            } catch (Exception e)   {
//                logger.info("Exception of  getOneAtmCardProfile " + e.getMessage());
//                return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneAtmCardProfile please check with your provider !");
//
//            }
//        }
//
//
//    public  ResponseApiJson<?> updateCardProfile(CardProfile cardProfile) throws IllegalAccessException {
//        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
//        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
//        try{
//            Optional<CardProfile> cardProfileto = cardRepository.findById(cardProfile.getId());
//            GlobalVars globalVars = new GlobalVars();
//            if (cardProfileto.isPresent()){
//                CardProfile cardProfile1 = (CardProfile)  globalVars.construct(CardProfile.class, cardProfile , cardProfileto.get());
//                logger.info("cardProfileto " + cardProfile1.toString());
//                cardRepository.save(cardProfile1);
//
//                // Track user activity
//                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "AtmCardProfile", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Update AtmCardProfile", "AtmCardProfile updated successfully", new Date());
//                userActivityTrackingRepository.save(userTrace);
//
//                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  AtmCardProfile  sucessfuly  !");
//
//
//            } else {
//
//                // Track user activity
//                UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Update", "AtmCardProfile", "000.000.00.00", "Failed", GlobalVars.ALREADYEXIST, "Update AtmCardProfile", "this AtmCardProfile  does not exit !", new Date());
//                userActivityTrackingRepository.save(userTrace);
//
//                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this AtmCardProfile  does not exit   !");
//
//            }
//        } catch (Exception e) {
//            logger.info("Exception of getOneAtmCardProfile "  + e.getMessage());
//            // Track user activity for exception
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Update", "AtmCardProfile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Update AtmCardProfile", " Some issues in updateAtmCardProfile please check with your provider !", new Date());
//            userActivityTrackingRepository.save(userTrace);
//
//            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateAtmCardProfile please check with your provider !");
//
//
//        }
//    }
//
//    public  ResponseApiJson<?> deleteCardProfile(CardProfileId id){
//        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
//        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
//        try {
//            Optional<CardProfile> cardProfile = cardRepository.findById(id);
//            cardProfile.ifPresent(cardRepository ::delete);
//
//            // Track user activity
//            UserActivityTracking userTrace =new UserActivityTracking(userM.get().getUserCode(), "Delete", "AtmCardProfile", "000.000.00.00", "Successfully", GlobalVars.SUCCESS, "Delete AtmCardProfile", "AtmCardProfile deleted successfully", new Date());
//            userActivityTrackingRepository.save(userTrace);
//
//            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete AtmCardProfile succesfully !");
//        }catch (Exception e){
//            logger.info("Delete AtmCardProfile Exception: " +e.getMessage());
//            // Track user activity for exception
//            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Delete", "AtmCardProfile", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Delete AtmCardProfile", " Some issues in deleteAtmCardProfile please check with your provider !", new Date());
//            userActivityTrackingRepository.save(userTrace);
//
//
//            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteAtmCardProfile terminated with issue");
//        }
//    }
//
//
// /*   public void updateCardProfile(CardProfileId cardProfileId, CardProfile updatedCardProfile) {
//        if (cardRepository.existsById(cardProfileId)) {
//            updatedCardProfile.setId(cardProfileId);
//            cardRepository.save(updatedCardProfile);
//        }
//    }*/
//
//
//
//   /* public void deleteCardProfile(CardProfileId cardProfileId) {
//        cardRepository.deleteById(cardProfileId);
//    }*/
//}
