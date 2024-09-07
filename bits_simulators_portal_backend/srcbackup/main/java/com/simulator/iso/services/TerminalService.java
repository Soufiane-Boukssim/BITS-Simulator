package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.TerminalParam;
import com.simulator.entities.TerminalParamId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.TerminalRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public  class TerminalService {
    private final Logger logger = LogManager.getLogger(TerminalService.class);
    @Autowired
    TerminalRepository terminalRepository;



    public TerminalParam getTerminalParam(TerminalParamId id) { return terminalRepository.findById(id).orElse(null);
    }

    public List<TerminalParam> getFilteredTerminalList(String bankCode, Character terProtocol) {
        return terminalRepository.findByBankCodeAndTerProtocol(bankCode, terProtocol);
    }




    public ResponseApiJson<List<TerminalParam>> getAllTerminalParams() {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            List<TerminalParam> TerminalParams = terminalRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all Terminal succesfully  !", TerminalParams);

        } catch (Exception e) {
            logger.info("Exception of this::getAllTerminalParams " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllTerminalParams please check with your provider !");

        }


    }

    public ResponseApiJson<List<TerminalParam>> getOneTerminalParam(TerminalParamId id) {
        String idRequest = "MSS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<TerminalParam> terminalParam = terminalRepository.findById(id);
            List<TerminalParam> terminalParams = new ArrayList<>();
            terminalParam.ifPresent(terminalParams::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one terminalParam succesfully  !" , terminalParams);
        } catch (Exception e)   {
            logger.info("Exception of  getOneTerminalParam" + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneTerminalParam please check with your provider !");

        }
    }

    public ResponseApiJson<?> addTerminalParam(TerminalParam terminalParam){
        String idRequest = "AM_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(terminalParam.toString());
        try {
            Optional<TerminalParam> terminalParamtoCheck = terminalRepository.findById(terminalParam.getId());
            if ( terminalParamtoCheck.isEmpty()) {
                terminalRepository.save(terminalParam);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Insertde terminalParam sucessfully  !");
            }else {
                return  new ResponseApiJson<>(idRequest , GlobalVars.ALREADYEXIST , "this TerminalParam already exist !");

            }
        } catch (Exception e ){
            logger.info("Exception of getOneTerminalParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in addTerminalParam please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateTerminalParam(TerminalParam terminalParam) throws IllegalAccessException {
        String  idRequest = "UMS_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<TerminalParam> terminalParamto = terminalRepository.findById(terminalParam.getId());
            GlobalVars globalVars = new GlobalVars();
            if (terminalParamto.isPresent()){
                TerminalParam terminalParam1 = (TerminalParam)  globalVars.construct(TerminalParam.class, terminalParam , terminalParamto.get());
                logger.info("terminalParamto " + terminalParam1.toString());
                terminalRepository.save(terminalParam1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  terminalParam  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this terminalParam  does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneTerminalParam "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateTerminalService please check with your provider !");


        }
    }

    public  ResponseApiJson<?> deleteTerminalParam(TerminalParamId id){
        String  idRequest = "DM_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<TerminalParam> terminalParam = terminalRepository.findById(id);
            terminalParam.ifPresent(terminalRepository ::delete);
            return  new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Delete TerminalParam succesfully !");
        }catch (Exception e){
            logger.info("Delete mtiDef Exception: " +e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE ,"deleteTerminalParam terminated with issue");
        }
    }

}





