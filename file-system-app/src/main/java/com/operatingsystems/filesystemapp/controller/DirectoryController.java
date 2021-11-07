package com.operatingsystems.filesystemapp.controller;

import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.service.DirectoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DirectoryController
 * Where all the stuff relating to directory manipulation request comes
 */
@RestController
@RequestMapping("/api/dir")
public class DirectoryController {

    private final DirectoryServiceImpl directoryService;

    @Autowired
    public DirectoryController(DirectoryServiceImpl directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping(value = "/getDirectory/{username}", produces = {MediaType.APPLICATION_XML_VALUE})
    public Directory getDirectory(@PathVariable String username)
    {
        return this.directoryService.getDirectory(username);
    }

}
