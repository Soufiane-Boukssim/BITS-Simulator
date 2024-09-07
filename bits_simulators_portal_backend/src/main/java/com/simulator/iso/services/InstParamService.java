package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.InstParam;
import com.simulator.entities.InstParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.InstRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InstParamService {
    private final Logger logger = LogManager.getLogger(InstParamService.class);
    @Autowired
    InstRepository instRepository;


    public ResponseApiJson<List<InstParam>> getAllInstParams(InstParamId instParamId) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<InstParam> instParams = instRepository.findById_BankCodeAndId_InstProtocol(instParamId.getBankCode(), instParamId.getInstProtocol());
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all instParams successfully  !", instParams);
        } catch (Exception e) {
            logger.info("Exception of this::getAllInstParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllInstParams please check with your provider !");
        }
    }

    public ResponseApiJson<List<InstParam>> getOneInstParam(InstParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<InstParam> instParam = instRepository.findById(id);
            List<InstParam> instParams = new ArrayList<>();
            instParam.ifPresent(instParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get one instParam successfully  !", instParams);
        } catch (Exception e) {
            logger.info("Exception of  getOneInstParam " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " some issues in getOneInstParam please check with your provider !");
        }
    }

    public ResponseApiJson<?> addInstParam(InstParam instParam) {
        String idRequest = "AM_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(instParam.toString());
        try {
            Optional<InstParam> instParamtoCheck = instRepository.findById(instParam.getId());
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

    public ResponseApiJson<?> updateInstParam(InstParam instParam) throws IllegalAccessException {
        String idRequest = "UMS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<InstParam> instParamto = instRepository.findById(instParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (instParamto.isPresent()) {
                InstParam instParam1 = (InstParam) globalVars.construct(InstParam.class, instParam, instParamto.get());
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

    public ResponseApiJson<?> deleteInstParam(InstParamId id) {
        String idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<InstParam> instParam = instRepository.findById(id);
            instParam.ifPresent(instRepository::delete);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete instParam successfully !");
        } catch (Exception e) {
            logger.info("Delete instParam Exception: " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteInstParam terminated with issue");
        }
    }
}
