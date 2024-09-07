package com.simulator.globalService;

import com.simulator.config.GlobalVars;
import com.simulator.entities.Bank;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.BankRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private final Logger logger = LogManager.getLogger(BankService.class);
    @Autowired
    BankRepository bankRepository;

    public ResponseApiJson<List<Bank>> getAllBanks() {
        String idRequest = "GAB_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<Bank> banks = bankRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all banks successfully !", banks);
        } catch (Exception e) {
            logger.info("Exception of getAllBank " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllBanks please check with your provider !");
        }
    }

    public ResponseApiJson<List<Bank>> getOneBank(String id) {
        String idRequest = "GOB_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<Bank> bank = bankRepository.findById(id);
            List<Bank> banks = new ArrayList<>();
            bank.ifPresent(banks::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get one bank successfully !", banks);
        } catch (Exception e) {
            logger.info("Exception of getOneBank " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getOneBank please check with your provider !");
        }
    }

    public ResponseApiJson<?> addBank(Bank bank) {
        String idRequest = "AB_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<Bank> bankToCheck = bankRepository.findById(bank.getId());
            if (bankToCheck.isEmpty()) {
                bankRepository.save(bank);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted bank successfully !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "This bank already exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneBank " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in addBank please check with your provider !");
        }
    }

    public ResponseApiJson<?> updateBank(Bank bank) throws IllegalAccessException {
        String idRequest = "UB_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<Bank> bankTo = bankRepository.findById(bank.getId());
            GlobalVars globalVars = new GlobalVars();
            if (bankTo.isPresent()) {
                Bank bank1 = (Bank) globalVars.construct(Bank.class, bank, bankTo.get());
                logger.info("BankTo ########### " + bank1.toString());
                bankRepository.save(bank1);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Updated bank successfully !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "This bank does not exist !");
            }

        } catch (Exception e) {
            logger.info("Exception of getOneBank " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in updateBank please check with your provider !");
        }

    }

    public ResponseApiJson<?> deleteBank(String id) {
        String idRequest = "DB_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<Bank> bank = bankRepository.findById(id);

            bank.ifPresent(bankRepository::delete);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Deleted bank successfully !");


        } catch (Exception e) {
            logger.info("Delete bank Exception: " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteBank terminated with issue");
        }
    }
}
