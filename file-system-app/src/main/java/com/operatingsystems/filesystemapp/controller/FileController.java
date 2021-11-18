package com.operatingsystems.filesystemapp.controller;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import com.operatingsystems.filesystemapp.service.FileSystemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * FileController
 * Where all the stuff relating to plain text files manipulation requests comes
 */
@RestController()
@RequestMapping("/api/file")
public class FileController {

    private final FileSystemServiceImpl fileSystemService;

    @Autowired
    public FileController(FileSystemServiceImpl fileSystemService)
    {
        this.fileSystemService = fileSystemService;
    }

    @GetMapping(value = "/getFile/{username}/{fileId}", produces = {MediaType.APPLICATION_XML_VALUE})
    public PlainTextFile getFile(@PathVariable String username, @PathVariable String fileId) {
        return this.fileSystemService.getFileProperties(username, fileId);
    }

    @PostMapping("/createFile")
    public ActionResult createFile(@RequestBody PlainTextFile newFile) {

        return this.fileSystemService.createFile(newFile);
    }

}
