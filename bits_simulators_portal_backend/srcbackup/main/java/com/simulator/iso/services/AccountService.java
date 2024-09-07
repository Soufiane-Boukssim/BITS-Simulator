package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.AccountDef;
import com.simulator.entities.AccountDefId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.AccountRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class AccountService {
    private final Logger logger = LogManager.getLogger(AccountService.class);
    @Autowired
    AccountRepository accountRepository;



    public AccountDef getAccountDef(AccountDefId id) {
        return accountRepository.findById(id).orElse(null);
    }



    public ResponseApiJson<List<AccountDef>> getFilteredAccountList(String bankCode, Character accProtocol) {
        String idRequest = "GFAL" + new Date().getTime() + (Math.random() * 9999);
        try {
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all accountsuccesfully  !", accountRepository.findByBankCodeAndAccProtocol(bankCode, accProtocol));
        }catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getFilteredAccountList please check with your provider !");

        }


    }

    public ResponseApiJson<List<AccountDef>> getAllAccountDefs() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<AccountDef> accountDefs = accountRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all accountsuccesfully  !", accountDefs);

        } catch (Exception e) {
            logger.info("Exception of this::getAllAccountDefs " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllAccountDefs please check with your provider !");

        }


    }

    public ResponseApiJson<List<AccountDef>> getOneAccountDef(AccountDefId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<AccountDef> accountDef = accountRepository.findById(id);
            List<AccountDef> accountDefs = new ArrayList<>();
            accountDef.ifPresent(accountDefs::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get one accountDef succesfully  !", accountDefs);
        } catch (Exception e) {
            logger.info("Exception of  getOneAccountDef " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " some issues in getOneAccountDef please check with your provider !");

        }
    }

    public ResponseApiJson<?> addAccountDef(AccountDef accountDef) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(accountDef.toString());
        try {
            Optional<AccountDef> accountDeftoCheck = accountRepository.findById(accountDef.getId());
            if (accountDeftoCheck.isEmpty()) {
                accountRepository.save(accountDef);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde accountDef sucessfully  !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this AccountDef already exist !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneAccountDef " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addAccountDef please check with your provider !");
        }
    }

    public ResponseApiJson<?> updateAccountDef(AccountDef accountDef) throws IllegalAccessException {
        String idRequest = "UMS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<AccountDef> accountDefto = accountRepository.findById(accountDef.getId());
            GlobalVars globalVars = new GlobalVars();
            if (accountDefto.isPresent()) {
                AccountDef accountDef1 = (AccountDef) globalVars.construct(AccountDef.class, accountDef, accountDefto.get());
                logger.info("accountDefto " + accountDef1.toString());
                accountRepository.save(accountDef1);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update  accountDef  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "this accountDef  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneMtiDef " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateAccountService please check with your provider !");


        }
    }

    public ResponseApiJson<?> deleteAccountDef(AccountDefId id) {
        String idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<AccountDef> accountDef = accountRepository.findById(id);
            accountDef.ifPresent(accountRepository::delete);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete accountDef succesfully !");
        } catch (Exception e) {
            logger.info("Delete accountDef Exception: " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteAccountDef terminated with issue");
        }
    }
}





