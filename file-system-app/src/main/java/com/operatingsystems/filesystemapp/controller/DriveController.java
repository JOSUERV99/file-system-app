package com.operatingsystems.filesystemapp.controller;


import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.service.DriveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/createDrive/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult createDrive(@PathVariable String username) {return this.driveService.createDrive(username);}

    @GetMapping(value = "/getDrive/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult getDrive(@PathVariable String username)
    {
        return this.driveService.createDrive(username);
    }
}