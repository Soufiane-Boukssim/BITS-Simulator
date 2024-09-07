package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.MccDef;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.entities.pos.PosMccDef;
import com.simulator.entities.pos.PosMccDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.pos.PosMccRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class PosMccService {
    private final Logger logger = LogManager.getLogger(PosMccService.class);

    @Autowired
    PosMccRepository mccRepository;

    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;




    public ResponseApiJson<List<PosMccDef>> getAllMccDefs(PosMccDefId id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("id==>"+id);
        try {
            List<PosMccDef> mccDefs = mccRepository.findByBankCodeAndMccProtocol(id.getBankCode(), id.getMccProtocol());

            UserActivityTracking userTrace = new UserActivityTracking(userM.isPresent() ? userM.get().getUserCode() : null,
                    "Retrieve", "MccDefs", "000.000.00.00", "Success", GlobalVars.SUCCESS,
                    "Retrieve MccDefs", "MccDefs retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all MccDef succesfully  !", mccDefs);


        } catch (Exception e) {
            logger.info("Exception of this::getAllMccDefs " + e.getMessage());

            UserActivityTracking userTrace = new UserActivityTracking(userM.isPresent() ? userM.get().getUserCode() : null,
                    "Retrieve", "MccDefs", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE,
                    "Retrieve MccDefs", "An error occurred while retrieving MccDefs", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllMccDefs please check with your provider !");

        }


    }



    public ResponseApiJson<List<PosMccDef>> getOneMccDef(PosMccDefId id) {
        String idRequest = "MCCS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosMccDef> mccDef = mccRepository.findById(id);
            List<PosMccDef> mccDefs = new ArrayList<>();
            mccDef.ifPresent(mccDefs::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Get one mccDef successfully !", mccDefs);
        } catch (Exception e) {
            logger.info(("Exception of getOneMccDef" + e.getMessage()));
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getOneMccDef sucessfully !");
        }
    }

    public ResponseApiJson<?> addMccDef(PosMccDef mccDef) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info(mccDef.toString());
        try {
            Optional<PosMccDef> mccDefOptional = mccRepository.findById(mccDef.getId());
            if (mccDefOptional.isEmpty()) {
                mccRepository.save(mccDef);

                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "mcc_def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add Mcc","Add Mcc with id : "+mccDef.getId().getMccCode()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Insert de MccDEf succesfully");
            } else {

                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "mcc_def","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"Add Mcc","Account with id : "+mccDef.getId().getMccCode()+" already exist !",new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this MccDef already exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneMccDef " + e.getMessage());

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "mcc_def","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add Mcc","Some issues in addMccDef please check provider !",new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " Some issues in addMccDef please check provider !");
        }
    }




    public ResponseApiJson<?> updateMccDef(PosMccDef mccDef) throws IllegalAccessException {
        String idRequest = "UMCC_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<PosMccDef> mccDefOptional1 = mccRepository.findById(mccDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (mccDefOptional1.isPresent()) {
                PosMccDef mccDef1 = (PosMccDef) globalVars.construct(MccDef.class, mccDef, mccDefOptional1.get());
                logger.info("mccDefOptional1 " + mccDef1.toString());
                mccRepository.save(mccDef1);

                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "mcc_def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Update Mcc","Update Mcc with id : "+mccDef.getId().getMccCode()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update  mccDef  sucessfuly  !");
            } else {

                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "mcc_def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update Mcc","Mcc with id : "+mccDef.getId().getMccCode()+" does not exist",new Date());
                userActivityTrackingRepository.save(userTrace);

                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "this MccDef  does not exit !");
            }

        } catch (Exception e) {
            logger.info("Exception of getOneMccDef " + e.getMessage());

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "mcc_def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update Mcc","Some issues in updateMccDEfService please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateMccDEfService please check with your provider !");
        }
    }
    public ResponseApiJson<?> deleteMccDef(PosMccDefId id){
        String idRequest = "DMCC_" + new  Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<PosMccDef> mccDef = mccRepository.findById(id);
            mccDef.ifPresent(mccRepository :: delete);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "mcc_def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Delete Mcc","Delete Mcc with id : "+id+" successfull",new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS ,"Delete mccDef successfully");
        }catch ( Exception e ) {
            logger.info("Delete mccDef Exception  " + e.getMessage());

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "mcc_def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Delete Mcc","Delete MccDef terminated with issue",new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE,"Delete MccDef terminated with issue");
        }
    }
}


