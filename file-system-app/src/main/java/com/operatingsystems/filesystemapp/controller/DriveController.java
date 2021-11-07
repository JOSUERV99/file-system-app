package com.operatingsystems.filesystemapp.controller;


import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.service.DriveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DriveController
 * Where all the stuff relating to drive manipulation request comes
 */
@RestController
@RequestMapping("/api/drive")
public class DriveController {

    private final DriveServiceImpl driveService;

    @Autowired
    public DriveController(DriveServiceImpl driveService)
    {
        this.driveService = driveService;
    }

    @GetMapping(value = "/getDrive/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult getDirectory(@PathVariable String username)
    {
        return this.driveService.getDrive(username);
    }

}