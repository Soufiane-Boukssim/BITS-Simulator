package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.AccountDef;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.entities.pos.PosAccountDef;
import com.simulator.entities.pos.PosAccountDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.repository.pos.PosAccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class PosAccountService {
    private final Logger logger = LogManager.getLogger(PosAccountService.class);
    @Autowired
    PosAccountRepository accountRepository;


    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;


    public ResponseApiJson<List<PosAccountDef>> getAllAccountDefs(PosAccountDefId id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            List<PosAccountDef> accountDefs = accountRepository.findByBankCodeAndAccProtocol(id.getBankCode(), id.getAccProtocol());

            //UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Accounts", "000.000.00.00", "Success", GlobalVars.SUCCESS, " Retrieve Accounts", "Accounts retrieved successfully", new Date());
            //userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all accountsuccesfully  !", accountDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());

            //UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", " Retrieve Accounts", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE,  " Retrieve Accounts", "An error occurred while retrieving Accounts ,please check with your provider !", new Date());
            //userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllAccountDefs please check with your provider !");

        }


    }

    public ResponseApiJson<List<PosAccountDef>> getOneAccountDef(PosAccountDefId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<PosAccountDef> accountDef = accountRepository.findById(id);
            List<PosAccountDef> accountDefs = new ArrayList<>();
            accountDef.ifPresent(accountDefs::add);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve one account ", "account_def", "000.000.00.00", "Success", GlobalVars.SUCCESS, " Retrieved Account", " Account retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get one accountDef succesfully  !", accountDefs);
        } catch (Exception e) {
            logger.info("Exception of  getOneAccountDef " + e.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Retrieve", "account_def","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Retrieved Account","Some issues in addAccountDef please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " some issues in getOneAccountDef please check with your provider !");

        }
    }

    public ResponseApiJson<?> addAccountDef(PosAccountDef accountDef) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info(accountDef.toString());
        try {
            Optional<PosAccountDef> accountDeftoCheck = accountRepository.findById(accountDef.getId());
            if (accountDeftoCheck.isEmpty()) {
                accountRepository.save(accountDef);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "account_def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Add Account","Add Account with id : "+accountDef.getId().getAccCode()+" successfull",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde accountDef sucessfully  !");
            } else {

                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "account_def","000.000.00.00", "Failed", GlobalVars.ALREADYEXIST,"Add Account","Account with id : "+accountDef.getId().getAccCode()+" already exist !",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this AccountDef already exist !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneAccountDef " + e.getMessage());
            // user Activity tracking Hamza 29.02.2024 start
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Add", "account_def","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Add Account","Some issues in addAccountDef please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);
            // user Activity tracking Hamza 29.02.2024 end
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addAccountDef please check with your provider !");
        }
    }

    public ResponseApiJson<?> updateAccountDef(PosAccountDef accountDef) throws IllegalAccessException {
        String idRequest = "UMS_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<PosAccountDef> accountDefto = accountRepository.findById(accountDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (accountDefto.isPresent()) {
                PosAccountDef accountDef1 = (PosAccountDef) globalVars.construct(AccountDef.class, accountDef, accountDefto.get());
                logger.info("accountDefto " + accountDef1.toString());
                accountRepository.save(accountDef1);
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "account_def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Update Account","Update Account with id : '"+accountDef.getId().getAccCode()+"' successfull",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update  accountDef  sucessfuly  !");

            } else {
                // user Activity tracking Hamza 29.02.2024 start
                UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "account_def","000.000.00.00", "Failed", GlobalVars.NOTEXIST,"Update Account","Account with id : "+accountDef.getId().getAccCode()+" does not exist !",new Date());
                userActivityTrackingRepository.save(userTrace);
                // user Activity tracking Hamza 29.02.2024 end
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "this accountDef  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneMtiDef " + e.getMessage());

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Update", "account_def","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Update Account","Some issues in updateAccountDefService please check with your provider !",new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateAccountService please check with your provider !");


        }
    }

    public ResponseApiJson<?> deleteAccountDef(PosAccountDefId id) {
        String idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        try {
            Optional<PosAccountDef> accountDef = accountRepository.findById(id);
            accountDef.ifPresent(accountRepository::delete);

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "account_def","000.000.00.00", "Success", GlobalVars.SUCCESS,"Delete Account","Deleting Account with id : "+id+" successfull",new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete accountDef succesfully !");
        } catch (Exception e) {
            logger.info("Delete accountDef Exception: " + e.getMessage());

            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(),"Delete", "account_def","000.000.00.00", "Failed", GlobalVars.EXCEPTIONSISSUE,"Delete Account","deleteAccountDef terminated with issue",new Date());
            userActivityTrackingRepository.save(userTrace);

            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteAccountDef terminated with issue");
        }
    }
}





