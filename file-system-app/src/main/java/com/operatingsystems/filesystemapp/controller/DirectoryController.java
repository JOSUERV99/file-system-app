package com.operatingsystems.filesystemapp.controller;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.service.DirectoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * DirectoryController
 * Where all the stuff relating to directory manipulation request comes
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/dir")
public class DirectoryController {

    private final DirectoryServiceImpl directoryService;

    @Autowired
    public DirectoryController(DirectoryServiceImpl directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping(value = "/getDirectory/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Directory getDirectory(@PathVariable String username) {
        return this.directoryService.getDirectory(username);
    }

    @PostMapping("/createDirectory/{username}/{DirId}")
    public ActionResult createDir(@PathVariable String username, @PathVariable String DirId, @RequestBody Directory newDir) {
        return this.directoryService.createVirtualDirectory(username, DirId, newDir);
    }

}
