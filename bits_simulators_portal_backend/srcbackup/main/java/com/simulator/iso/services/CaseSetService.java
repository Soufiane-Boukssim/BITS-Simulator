package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.CaseSetInfo;
import com.simulator.entities.CasesSetInfoId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.CaseSetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class CaseSetService {
    private final Logger logger = LogManager.getLogger(CaseSetService.class);
    @Autowired
    CaseSetRepository caseSetRepository;


    public CaseSetInfo getCaseSetInfo(CasesSetInfoId id) {return caseSetRepository.findById(id).orElse(null);
    }


    public List<CaseSetInfo> getFilteredCaseSetList(String bankCode, Character caseSetProtocole) {
        return caseSetRepository.findByBankCodeAndCaseSetProtocole(bankCode, caseSetProtocole);
    }



    public ResponseApiJson<List<CaseSetInfo>> getAllCaseSetInfos() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<CaseSetInfo> caseSetInfos = caseSetRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all caseSet succesfully  !", caseSetInfos);

        } catch (Exception e) {
            logger.info("Exception of this::getAllCaseSetInfos " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllCaseSetInfos please check with your provider !");

        }


    }

    public ResponseApiJson<List<CaseSetInfo>> getOneCaseSetInfo(CasesSetInfoId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CaseSetInfo> caseSetInfo = caseSetRepository.findById(id);
            List<CaseSetInfo> caseSetInfos = new ArrayList<>();
            caseSetInfo.ifPresent(caseSetInfos::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one caseSetInfo succesfully  !" , caseSetInfos);
        } catch (Exception e)   {
            logger.info("Exception of  getOneCaseSetInfo " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneCaseSetInfo please check with your provider !");

        }
    }

    public ResponseApiJson<?> addCaseSetInfo(CaseSetInfo caseSetInfo){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(caseSetInfo.toString());
        try {
            Optional<CaseSetInfo> caseSetInfotoCheck = caseSetRepository.findById(caseSetInfo.getId());
            if ( caseSetInfotoCheck.isEmpty()) {
                caseSetRepository.save(caseSetInfo);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde caseSetInfo sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this CaseSetInfo already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneCaseSetInfo "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addCaseSetInfo please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateCaseSetInfo(CaseSetInfo caseSetInfo) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<CaseSetInfo> caseSetInfoto = caseSetRepository.findById(caseSetInfo.getId());
            GlobalVars globalVars = new GlobalVars();
            if (caseSetInfoto.isPresent()){
                CaseSetInfo caseSetInfo1 = (CaseSetInfo)  globalVars.construct(CaseSetInfo.class, caseSetInfo , caseSetInfoto.get());
                logger.info("caseSetinfoto " + caseSetInfo1.toString());
                caseSetRepository.save(caseSetInfo1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  caseSetInfo  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this caseSetInfo  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneCaseSetInfo "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateCaseSetService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteCaseSetInfo(CasesSetInfoId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<CaseSetInfo> caseSetInfo = caseSetRepository.findById(id);
            caseSetInfo.ifPresent(caseSetRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete caseSetInfo succesfully !");
        }catch (Exception e){
            logger.info("Delete caseSetInfo Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteCaseSetInfo terminated with issue");
        }
    }

}





