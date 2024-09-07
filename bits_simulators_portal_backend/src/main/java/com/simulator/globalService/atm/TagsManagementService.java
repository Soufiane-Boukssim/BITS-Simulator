package com.simulator.globalService.atm;

import com.simulator.entities.atm.TagsManagement;
import com.simulator.repository.atm.TagsManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagsManagementService {

    @Autowired
    private TagsManagementRepository tagsManagementRepository;

    // Save or update a TagsManagement
    public TagsManagement saveOrUpdateTagsManagement(TagsManagement tagsManagement) {
        return tagsManagementRepository.save(tagsManagement);
    }

    // Get a TagsManagement by tag code
    public Optional<TagsManagement> getTagsManagementByTagCode(String tagCode) {
        return tagsManagementRepository.findById(tagCode);
    }

    // Get all TagsManagement entries
    public List<TagsManagement> getAllTagsManagement() {
        return tagsManagementRepository.findAll();
    }

    // Delete a TagsManagement by tag code
    public void deleteTagsManagementByTagCode(String tagCode) {
        tagsManagementRepository.deleteById(tagCode);
    }
}
