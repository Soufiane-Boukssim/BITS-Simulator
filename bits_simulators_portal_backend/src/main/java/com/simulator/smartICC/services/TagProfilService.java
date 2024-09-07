package com.simulator.smartICC.services;

import com.simulator.atm.controllers.controlleratm.LogNDCController;
import com.simulator.config.GlobalVars;
import com.simulator.entities.UserActivityTracking;
import com.simulator.entities.UserManagement;
import com.simulator.globalModels.ResponseApiJson;
import com.simulator.repository.UserActivityTrackingRepository;
import com.simulator.smartICC.models.TagProfil;
import com.simulator.smartICC.repositories.TagProfilRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TagProfilService {

    private final TagProfilRepository tagProfilRepository;

    @Autowired
    public TagProfilService(TagProfilRepository tagProfilRepository) {
        this.tagProfilRepository = tagProfilRepository;
    }


    private final Logger logger = LogManager.getLogger(LogNDCController.class);
    @Autowired
    UserActivityTrackingRepository userActivityTrackingRepository;

    @Autowired
    GlobalVars globalVars;

//    public List<TagProfil> getAllTagProfils() {
//        return tagProfilRepository.findAll();
//    }


    public ResponseApiJson<List<TagProfil>> getAllTagProfils() {
        String idRequest = "TAG_" + new Date().getTime() + (Math.random() * 9999);
        Optional<UserManagement> userM = globalVars.getUser(GlobalVars.getBearerTokenHeader());
        logger.info("---->userM:" + userM);
        try {
            List<TagProfil> tagProfils = tagProfilRepository.findAll();
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Tag profiles", "000.000.00.00", "Success", GlobalVars.SUCCESS, "Retrieve tag profiles", "Tag profiles retrieved successfully", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.SUCCESS, "Getting tag profiles successful", tagProfils);
        } catch (Exception ex) {
            logger.error("Exception in getAllTagProfils: " + ex.getMessage());
            UserActivityTracking userTrace = new UserActivityTracking(userM.get().getUserCode(), "Retrieve", "Tag profiles", "000.000.00.00", "Error", GlobalVars.EXCEPTIONSISSUE, "Retrieve tag profiles", "An error occurred while retrieving tag profiles", new Date());
            userActivityTrackingRepository.save(userTrace);
            return new ResponseApiJson<>(idRequest, GlobalVars.EXCEPTIONSISSUE, "Some issues in getAllTagProfils, please check with your provider !");
        }
    }

    public TagProfil saveTagProfil(TagProfil tagProfil) {
        return tagProfilRepository.save(tagProfil);
    }

    public Optional<TagProfil> getTagProfilById(Long id) {
        return tagProfilRepository.findById(id);
    }
    // Dans le service
    public List<TagProfil> retrieveAllTagProfils() {
        return tagProfilRepository.findAll();
    }

    public void deleteTagProfil(Long id) {
        tagProfilRepository.deleteById(id);
    }


    // Ajoutez d'autres méthodes pour la mise à jour si nécessaire
}
