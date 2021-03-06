package com.operatingsystems.filesystemapp.controller;


import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.PasswordWrapper;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import com.operatingsystems.filesystemapp.service.DriveServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * DriveController
 * Where all the stuff relating to drive manipulation request comes
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/drive")
public class DriveController {

    private final DriveServiceImpl driveService;

    @Autowired
    public DriveController(DriveServiceImpl driveService) {
        this.driveService = driveService;
    }

    @PostMapping(value = "/createDrive/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult createDrive(@PathVariable String username, @RequestBody  PasswordWrapper passwordWrapper) {
        return this.driveService.createDrive(username, passwordWrapper.getPassword());
    }

    @PostMapping(value = "/getDrive/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult getDrive(@PathVariable String username, @RequestBody PasswordWrapper passwordWrapper) {
        return this.driveService.getDrive(username, passwordWrapper.getPassword());
    }

    @GetMapping(value = "/getSharedFiles/{userName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult getSharedWithMeFiles(@PathVariable String userName) {
        return this.driveService.getSharedFiles(userName);
    }

    @GetMapping(value = "/getAllUserNames", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult getAllDrives() {
        return this.driveService.getAllDrives();
    }
}