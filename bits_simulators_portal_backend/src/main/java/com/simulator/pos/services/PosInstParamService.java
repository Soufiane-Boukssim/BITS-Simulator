package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.InstParam;
import com.simulator.entities.pos.PosInstParam;
import com.simulator.entities.pos.PosInstParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.pos.PosInstRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PosInstParamService {
    private final Logger logger = LogManager.getLogger(PosInstParamService.class);
    @Autowired
    PosInstRepository instRepository;


    public ResponseApiJson<List<PosInstParam>> getAllInstParams(PosInstParamId instParamId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosInstParam> instParams = instRepository.findById_BankCodeAndId_InstProtocol(instParamId.getBankCode(), instParamId.getInstProtocol());
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all instParams successfully  !", instParams);
        } catch (Exception e) {
            logger.info("Exception of this::getAllInstParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllInstParams please check with your provider !");
        }
    }

    public ResponseApiJson<List<PosInstParam>> getOneInstParam(PosInstParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosInstParam> instParam = instRepository.findById(id);
            List<PosInstParam> instParams = new ArrayList<>();
            instParam.ifPresent(instParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get one instParam successfully  !", instParams);
        } catch (Exception e) {
            logger.info("Exception of  getOneInstParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " some issues in getOneInstParam please check with your provider !");
        }
    }

    public ResponseApiJson<?> addInstParam(PosInstParam instParam) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(instParam.toString());
        try {
            Optional<PosInstParam> instParamtoCheck = instRepository.findById(instParam.getId());
            if (instParamtoCheck.isEmpty()) {
                instRepository.save(instParam);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Inserted instParam successfully  !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this InstParam already exists !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneInstParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in addInstParam please check with your provider !");
        }
    }

    public ResponseApiJson<?> updateInstParam(PosInstParam instParam) throws IllegalAccessException {
        String idRequest = "UMS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosInstParam> instParamto = instRepository.findById(instParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (instParamto.isPresent()) {
                PosInstParam instParam1 = (PosInstParam) globalVars.construct(InstParam.class, instParam, instParamto.get());
                logger.info("instParamto " + instParam1.toString());
                instRepository.save(instParam1);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Update instParam successfully  !");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.NOTEXIST, "this instParam does not exist   !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneInstParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in updateInstService please check with your provider !");
        }
    }

    public ResponseApiJson<?> deleteInstParam(PosInstParamId id) {
        String idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosInstParam> instParam = instRepository.findById(id);
            instParam.ifPresent(instRepository::delete);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete instParam successfully !");
        } catch (Exception e) {
            logger.info("Delete instParam Exception: " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteInstParam terminated with issue");
        }
    }
}
