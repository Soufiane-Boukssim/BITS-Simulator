package com.simulator.atm.services.script;

import com.simulator.atm.model.AtmIccProfil;
import com.simulator.atm.model.AtmIccProfilId;
import com.simulator.atm.repositories.reposcript.AtmIccRepository;
import com.simulator.config.GlobalVars;
import com.simulator.globalModels.ResponseApiJson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AtmIccService {

    private final Logger logger = LogManager.getLogger(AtmIccService.class);


    @Autowired
    AtmIccRepository iccRepository;

    public ResponseApiJson<List<AtmIccProfil>> getAllIccProfiles(AtmIccProfilId id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<AtmIccProfil> iccProfiles = iccRepository.findById_IcPrfAndId_IccProtocolAndId_BankCode(id.getIcPrf(),id.getIccProtocol(),id.getBankCode());

            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all iccProfiles succesfully  !", iccProfiles);


        } catch (Exception e) {
            logger.info("Exception of this::getAllIccProfiles " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllIccProfiles please check with your provider !");

        }


    }


    public ResponseApiJson<List<AtmIccProfil>> getOneIccProfile(AtmIccProfilId id) {
        String idRequest = "OICC_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<AtmIccProfil> PosIccProfile = iccRepository.findById(id);
            List<AtmIccProfil> iccProfiles = new ArrayList<>();
            PosIccProfile.ifPresent(iccProfiles::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one PosIccProfile succesfully  !" , iccProfiles);
        } catch (Exception e)   {
            logger.info("Exception of  getOneIccProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneIccProfile please check with your provider !");

        }
    }

    public ResponseApiJson<?> addIccProfiles(List<AtmIccProfil> iccProfiles) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            for (AtmIccProfil iccProfile :
                    iccProfiles) {

                iccRepository.save(iccProfile);

            }
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Insert de PosIccProfile succesfully");
        } catch (Exception e) {
            logger.info("Exception of getOneIccProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " Some issues in addIccProfile please check provider !");
        }
    }

    public ResponseApiJson<?> addIccProfile(AtmIccProfil iccProfile) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(iccProfile.toString());
        try {
            Optional<AtmIccProfil> iccProfileOptional = iccRepository.findById(iccProfile.getId());
            if (iccProfileOptional.isEmpty()) {
                iccRepository.save(iccProfile);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Insert de PosIccProfile succesfully");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this PosIccProfile already exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneIccProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " Some issues in addIccProfile please check provider !");
        }
    }

    public  ResponseApiJson<?> updateIccProfile(List<AtmIccProfil> iccProfiles) throws IllegalAccessException {
        String  idRequest = "UICCP_"  + new Date().getTime() + (Math.random() * 9999);
        List<AtmIccProfil> iccProfiles1=new ArrayList<>();
        try{
            if (iccProfiles.size()>0){
                iccProfiles1 = iccRepository.findById_IcPrfAndId_IccProtocolAndId_BankCode(iccProfiles.get(0).getId().getIcPrf(),iccProfiles.get(0).getId().getIccProtocol(),iccProfiles.get(0).getId().getBankCode());
                for (AtmIccProfil iccProfile1: iccProfiles1){
                    iccRepository.deleteById(iccProfile1.getId());
                }
            }
            for (AtmIccProfil iccProfile :
                    iccProfiles) {
                Optional<AtmIccProfil> optionalIccProfile = iccRepository.findById(iccProfile.getId());
                GlobalVars globalVars = new GlobalVars();

                AtmIccProfil iccProfile1 = (AtmIccProfil)  globalVars.construct(AtmIccProfil.class, iccProfile , optionalIccProfile.get());
                logger.info("optionalIccProfile " + iccProfile1.toString());
                iccRepository.save(iccProfile1);




            }
            return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  iccProfile  sucessfuly  !");
        } catch (Exception e) {
            logger.info("Exception of getOneIccProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateIccService please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateIccProfile(AtmIccProfil iccProfile) throws IllegalAccessException {
        String  idRequest = "UICCP_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<AtmIccProfil> optionalIccProfile = iccRepository.findById(iccProfile.getId());
            GlobalVars globalVars = new GlobalVars();
            if (optionalIccProfile.isPresent()){
                AtmIccProfil iccProfile1 = (AtmIccProfil)  globalVars.construct(AtmIccProfil.class, iccProfile , optionalIccProfile.get());
                logger.info("optionalIccProfile " + iccProfile1.toString());
                iccRepository.save(iccProfile1);
                return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  iccProfile  sucessfuly  !");

            } else {
                return new ResponseApiJson<>(idRequest , GlobalVars.NOTEXIST , "this iccProfile does not exit   !");

            }
        } catch (Exception e) {
            logger.info("Exception of getOneIccProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateIccService please check with your provider !");
        }
    }

    public  ResponseApiJson<?> deleteIccProfile(AtmIccProfilId id) {
        String idRequest = "DICCP_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<AtmIccProfil> iccProfile = iccRepository.findById(id);
            iccProfile.ifPresent(iccRepository::delete);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete iccProfile succesfully !");
        } catch (Exception e) {
            logger.info("Delete iccProfile Exception: " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteIccProfile terminated with issue");
        }


    }





    /*public  PosIccProfile deleteIccProfile(PosIccProfileId id) {
        String idRequest = "DICCP_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(iccProfile.toString());
        Optional<PosIccProfile> iccProfile = iccRepository.findById(id);
               if (iccProfile.isPresent()){
               deleteIccProfile(iccProfile.get().getId());

           } else  {
        }  return    null;
*/
}
