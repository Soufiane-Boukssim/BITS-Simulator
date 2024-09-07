package com.simulator.pos.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.pos.PosIccProfile;
import com.simulator.entities.pos.PosIccProfileId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.pos.PosIccRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PosIccService {
    private final Logger logger = LogManager.getLogger(PosIccService.class);


    @Autowired
    PosIccRepository iccRepository;



   /* public PosIccProfile getIccProfile(PosIccProfileId id) {
        return iccRepository.findById(id).orElse(null);
    }
*/


   /* public List<PosIccProfile> getFilteredIccList(String bankCode, String iccProtocol) {
        return iccRepository.findByBankCodeAndIccProtocole(bankCode, iccProtocol);
    }
*/


  /*  public ResponseApiJson<List<PosIccProfile>> getAllIccProfiles() {
        String idRequest = "ICCS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosIccProfile> IccProfiles = iccRepository.findAll();
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all icc succesfully  !", IccProfiles);

        } catch (Exception e) {
            logger.info("Exception of this::getAllIccProfiles " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllIccProfiles please check with your provider !");

        }

    }
*/

     /* public PosIccProfile addIccProfile(PosIccProfile iccProfile){
        String idRequest = "AICC_"  + new  Date().getTime() + (Math.random() * 9999);
        logger.info(iccProfile.toString());
            Optional<PosIccProfile> iccProfileOptional = iccRepository.findById(iccProfile.getId());
            if ( iccProfileOptional.isEmpty()) {
                return iccRepository.save(iccProfile);

            } else {
                return null;

            }

    }*/

      /*public  PosIccProfile deleteIccProfile(PosIccProfileId id) {
        String idRequest = "DICCP_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(iccProfile.toString());
        Optional<PosIccProfile> iccProfile = iccRepository.findById(id);
               if (iccProfile.isPresent()){
               deleteIccProfile(iccProfile.get().getId());

           } else  {
        }  return    null;
*/





    public ResponseApiJson<List<PosIccProfile>> getAllIccProfiles(PosIccProfileId id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<PosIccProfile> iccProfiles = iccRepository.findById_IcPrfAndId_IccProtocolAndId_BankCode(id.getIcPrf(),id.getIccProtocol(),id.getBankCode());

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all iccProfiles succesfully  !", iccProfiles);


        } catch (Exception e) {
            logger.info("Exception of this::getAllIccProfiles " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllIccProfiles please check with your provider !");

        }


    }


    public ResponseApiJson<List<PosIccProfile>> getOneIccProfile(PosIccProfileId id) {
        String idRequest = "OICC_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosIccProfile> PosIccProfile = iccRepository.findById(id);
            List<PosIccProfile> iccProfiles = new ArrayList<>();
            PosIccProfile.ifPresent(iccProfiles::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one PosIccProfile succesfully  !" , iccProfiles);
        } catch (Exception e)   {
            logger.info("Exception of  getOneIccProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneIccProfile please check with your provider !");

        }
    }

    public ResponseApiJson<?> addIccProfiles(List<PosIccProfile> iccProfiles) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            for (PosIccProfile iccProfile :
                    iccProfiles) {

                    iccRepository.save(iccProfile);

            }
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Insert de PosIccProfile succesfully");
        } catch (Exception e) {
            logger.info("Exception of getOneIccProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " Some issues in addIccProfile please check provider !");
        }
    }

    public ResponseApiJson<?> addIccProfile(PosIccProfile iccProfile) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(iccProfile.toString());
        try {
            Optional<PosIccProfile> iccProfileOptional = iccRepository.findById(iccProfile.getId());
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

    public  ResponseApiJson<?> updateIccProfile(List<PosIccProfile> iccProfiles) throws IllegalAccessException {
        String  idRequest = "UICCP_"  + new Date().getTime() + (Math.random() * 9999);
        List<PosIccProfile> iccProfiles1=new ArrayList<>();
        try{
        if (iccProfiles.size()>0){
            iccProfiles1 = iccRepository.findById_IcPrfAndId_IccProtocolAndId_BankCode(iccProfiles.get(0).getId().getIcPrf(),iccProfiles.get(0).getId().getIccProtocol(),iccProfiles.get(0).getId().getBankCode());
            for (PosIccProfile iccProfile1: iccProfiles1){
            iccRepository.deleteById(iccProfile1.getId());
            }
        }
            for (PosIccProfile iccProfile :
                    iccProfiles) {
                Optional<PosIccProfile> optionalIccProfile = iccRepository.findById(iccProfile.getId());
                GlobalVars globalVars = new GlobalVars();

                PosIccProfile iccProfile1 = (PosIccProfile)  globalVars.construct(PosIccProfile.class, iccProfile , optionalIccProfile.get());
                logger.info("optionalIccProfile " + iccProfile1.toString());
                iccRepository.save(iccProfile1);




            }
            return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  iccProfile  sucessfuly  !");
        } catch (Exception e) {
            logger.info("Exception of getOneIccProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateIccService please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateIccProfile(PosIccProfile iccProfile) throws IllegalAccessException {
        String  idRequest = "UICCP_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<PosIccProfile> optionalIccProfile = iccRepository.findById(iccProfile.getId());
            GlobalVars globalVars = new GlobalVars();
            if (optionalIccProfile.isPresent()){
                PosIccProfile iccProfile1 = (PosIccProfile)  globalVars.construct(PosIccProfile.class, iccProfile , optionalIccProfile.get());
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

    public  ResponseApiJson<?> deleteIccProfile(PosIccProfileId id) {
        String idRequest = "DICCP_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<PosIccProfile> iccProfile = iccRepository.findById(id);
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

