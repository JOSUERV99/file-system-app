package com.operatingsystems.filesystemapp.controller;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import com.operatingsystems.filesystemapp.service.DirectoryServiceImpl;
import com.operatingsystems.filesystemapp.service.FileSystemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fileSystem")
public class FileSystemController {

    private final FileSystemServiceImpl fileSystemService;

    @Autowired
    public FileSystemController(FileSystemServiceImpl fileSystemService)
    {
        this.fileSystemService = fileSystemService;
    }

    @DeleteMapping(value = "/deleteFile/{username}/{fileId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult removeFile(@PathVariable String username, @PathVariable String fileId) {
        return this.fileSystemService.removeFile(username, fileId);
    }

    @PostMapping(value = "/shareFile/{buddyUserName}/{ownerUserName}/{fileId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult shareFile(@PathVariable String buddyUserName,@PathVariable String ownerUserName, @PathVariable String fileId) {
        return this.fileSystemService.shareFile(fileId, buddyUserName , ownerUserName);
    }

    @GetMapping(value = "/getSharedFiles/{userName}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult getSharedWithMeFiles(@PathVariable String userName) {
        return this.fileSystemService.getSharedFiles(userName);
    }
}