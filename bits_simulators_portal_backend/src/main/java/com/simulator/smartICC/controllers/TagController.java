package com.simulator.smartICC.controllers;

// TagController.java

import com.simulator.smartICC.models.Tag;
import com.simulator.smartICC.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @PostMapping("/tags/all")
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

}
