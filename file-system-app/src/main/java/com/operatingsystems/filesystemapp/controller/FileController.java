package com.operatingsystems.filesystemapp.controller;

import com.operatingsystems.filesystemapp.model.PlainTextFile;
import com.operatingsystems.filesystemapp.service.FileSystemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileController
 * Where all the stuff relating to plain text files manipulation requests comes
 */
@RestController()
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private FileSystemServiceImpl fileSystemService;

    @GetMapping(value = "/getFile/{username}/{fileId}", produces = {MediaType.APPLICATION_XML_VALUE})
    public PlainTextFile getFile(@PathVariable String username, @PathVariable String fileId) {
        return this.fileSystemService.getFileProperties(username, fileId);
    }

}
