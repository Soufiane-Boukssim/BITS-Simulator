package com.simulator.iso.services;

import com.simulator.config.GlobalVars;
import com.simulator.entities.IccProfile;
import com.simulator.entities.IccProfileId;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.IccRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IccService {
    private final Logger logger = LogManager.getLogger(IccService.class);


    @Autowired
    IccRepository iccRepository;


    public ResponseApiJson<List<IccProfile>> getAllIccProfiles(IccProfileId id) {
        String idRequest = "MS_" + new Date().getTime() + (Math.random() * 9999);
        try {
            List<IccProfile> iccProfiles = iccRepository.findById_IcPrfAndId_IccProtocolAndId_BankCode(id.getIcPrf(),id.getIccProtocol(),id.getBankCode());

                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Get all iccProfiles succesfully  !", iccProfiles);


        } catch (Exception e) {
            logger.info("Exception of this::getAllIccProfiles " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "some issues in getAllIccProfiles please check with your provider !");

        }


    }


    public ResponseApiJson<List<IccProfile>> getOneIccProfile(IccProfileId id) {
        String idRequest = "OICC_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<IccProfile> IccProfile = iccRepository.findById(id);
            List<IccProfile> iccProfiles = new ArrayList<>();
            IccProfile.ifPresent(iccProfiles::add);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS , "Get one IccProfile succesfully  !" , iccProfiles);
        } catch (Exception e)   {
            logger.info("Exception of  getOneIccProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , " some issues in getOneIccProfile please check with your provider !");

        }
    }

    public ResponseApiJson<?> addIccProfiles(List<IccProfile> iccProfiles) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);

        try {
            for (IccProfile iccProfile :
                    iccProfiles) {

                    iccRepository.save(iccProfile);

            }
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Insert de IccProfile succesfully");
        } catch (Exception e) {
            logger.info("Exception of getOneIccProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " Some issues in addIccProfile please check provider !");
        }
    }

    public ResponseApiJson<?> addIccProfile(IccProfile iccProfile) {
        String idRequest = "AMCS_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(iccProfile.toString());
        try {
            Optional<IccProfile> iccProfileOptional = iccRepository.findById(iccProfile.getId());
            if (iccProfileOptional.isEmpty()) {
                iccRepository.save(iccProfile);
                return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, " Insert de IccProfile succesfully");
            } else {
                return new ResponseApiJson<>(idRequest, GlobalVars.ALREADYEXIST, "this IccProfile already exist !");
            }
        } catch (Exception e) {
            logger.info("Exception of getOneIccProfile " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, " Some issues in addIccProfile please check provider !");
        }
    }

    public  ResponseApiJson<?> updateIccProfile(List<IccProfile> iccProfiles) throws IllegalAccessException {
        String  idRequest = "UICCP_"  + new Date().getTime() + (Math.random() * 9999);
        List<IccProfile> iccProfiles1=new ArrayList<>();
        try{
        if (iccProfiles.size()>0){
            iccProfiles1 = iccRepository.findById_IcPrfAndId_IccProtocolAndId_BankCode(iccProfiles.get(0).getId().getIcPrf(),iccProfiles.get(0).getId().getIccProtocol(),iccProfiles.get(0).getId().getBankCode());
            for (IccProfile iccProfile1: iccProfiles1){
            iccRepository.deleteById(iccProfile1.getId());
            }
        }
            for (IccProfile iccProfile :
                    iccProfiles) {
                Optional<IccProfile> optionalIccProfile = iccRepository.findById(iccProfile.getId());
                GlobalVars globalVars = new GlobalVars();

                IccProfile iccProfile1 = (IccProfile)  globalVars.construct(IccProfile.class, iccProfile , optionalIccProfile.get());
                logger.info("optionalIccProfile " + iccProfile1.toString());
                iccRepository.save(iccProfile1);




            }
            return new ResponseApiJson<>(idRequest , GlobalVars.SUCCESS , "Update  iccProfile  sucessfuly  !");
        } catch (Exception e) {
            logger.info("Exception of getOneIccProfile "  + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE , "Some issues in updateIccService please check with your provider !");
        }
    }

    public  ResponseApiJson<?> updateIccProfile(IccProfile iccProfile) throws IllegalAccessException {
        String  idRequest = "UICCP_"  + new Date().getTime() + (Math.random() * 9999);
        try{
            Optional<IccProfile> optionalIccProfile = iccRepository.findById(iccProfile.getId());
            GlobalVars globalVars = new GlobalVars();
            if (optionalIccProfile.isPresent()){
                IccProfile iccProfile1 = (IccProfile)  globalVars.construct(IccProfile.class, iccProfile , optionalIccProfile.get());
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

    public  ResponseApiJson<?> deleteIccProfile(IccProfileId id) {
        String idRequest = "DICCP_" + new Date().getTime() + (Math.random() * 9999);
        try {
            Optional<IccProfile> iccProfile = iccRepository.findById(id);
            iccProfile.ifPresent(iccRepository::delete);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Delete iccProfile succesfully !");
        } catch (Exception e) {
            logger.info("Delete iccProfile Exception: " + e.getMessage());
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "deleteIccProfile terminated with issue");
        }


    }





    /*public  IccProfile deleteIccProfile(IccProfileId id) {
        String idRequest = "DICCP_" + new Date().getTime() + (Math.random() * 9999);
        logger.info(iccProfile.toString());
        Optional<IccProfile> iccProfile = iccRepository.findById(id);
               if (iccProfile.isPresent()){
               deleteIccProfile(iccProfile.get().getId());

           } else  {
        }  return    null;
*/


}

