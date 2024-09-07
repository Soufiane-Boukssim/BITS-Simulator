package com.simulator.smartICC.services;

import com.simulator.smartICC.models.EmvTag;
import com.simulator.smartICC.repositories.EmvTagRepository;
import com.simulator.smartICC.requests.GlobalResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmvTagService {
    private final Logger logger = LogManager.getLogger(EmvTagService.class);
    private final EmvTagRepository emvTagRepository;
    private final Map<String, List<EmvTag>> cachedData = new HashMap<>();

    @Autowired
    public EmvTagService(EmvTagRepository emvTagRepository) {
        this.emvTagRepository = emvTagRepository;
        populateCacheData();
    }

    private void populateCacheData() {
        try {
            List<EmvTag> allTags = emvTagRepository.findAll();
            cachedData.put("allData", allTags);
            logger.info("emv tags list cached, list size : " + allTags.size());
        } catch (Exception e) {
            logger.info("Exception when trying to cache all emv tags : " + e.getMessage());
        }
    }

public GlobalResponse<EmvTag> saveTag(EmvTag emvTag) {
        try {
            EmvTag savedTag = emvTagRepository.save(emvTag);
            populateCacheData(); // Mettre à jour la mémoire cache avec les données mises à jour
            return new GlobalResponse<>("000", "Tag saved successfully", savedTag);
        } catch (Exception e) {
            return new GlobalResponse<>("500", "Failed to save tag: " + e.getMessage(), null);
        }
    }

    public GlobalResponse<List<EmvTag>> getAllTags() {
        List<EmvTag> data = cachedData.get("allData");
        return new GlobalResponse<> (
                "000",
                "successful",
                data
        );
    }


    public EmvTag getTag(String id) {
        return emvTagRepository.findById(id).orElse(null);
    }
}