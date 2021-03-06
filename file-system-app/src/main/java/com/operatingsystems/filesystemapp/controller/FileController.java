package com.operatingsystems.filesystemapp.controller;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import com.operatingsystems.filesystemapp.service.FileSystemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
/**
 * FileController
 * Where all the stuff relating to plain text files manipulation requests comes
 */
@RestController()
@RequestMapping("/api/file")
@CrossOrigin(origins = "http://localhost:3000")
public class FileController {

    private final FileSystemServiceImpl fileSystemService;

    @Autowired
    public FileController(FileSystemServiceImpl fileSystemService)
    {
        this.fileSystemService = fileSystemService;
    }

    @GetMapping(value = "/getFile/{username}/{fileId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ActionResult getFile(@PathVariable String username, @PathVariable String fileId) {
        return this.fileSystemService.getFileProperties(username, fileId);
    }

    @PostMapping("/createFile/{username}/{DirId}")
    public ActionResult createFile(@PathVariable String username, @PathVariable String DirId, @RequestBody PlainTextFile newFile) {
        return this.fileSystemService.createFile(username, DirId, newFile);
    }

    @PostMapping("/modifyFileContent/{username}/{fileId}")
    public ActionResult modifyFileContent(@PathVariable String username,@PathVariable String fileId, @RequestBody PlainTextFile newModifiedFile) {
        return this.fileSystemService.modifyFileContent(username,fileId , newModifiedFile);
    }

    @PostMapping("/moveFile/{username}/{fileId}/{newDirId}")
    public ActionResult moveFile(@PathVariable String username,@PathVariable String fileId,@PathVariable String newDirId) {
        return this.fileSystemService.moveFile(username,fileId, newDirId);
    }

    @PostMapping("/copyFile/{username}/{fileId}/{newDirId}")
    //copy vv
    public ActionResult copyFile(@PathVariable String username,@PathVariable String fileId, @PathVariable String newDirId) {
        return this.fileSystemService.copyFromVirtualToVirtual(username, fileId,newDirId);
    }

    @PostMapping("/downloadFile/{username}/{fileId}")
    //copy vr
    public ActionResult downloadFile(@PathVariable String username, @PathVariable String fileId){
        return this.fileSystemService.copyFromVirtualToReal(username, fileId, FileSystemConstants.DEFAULT_FILE_SYSTEM_LOCATION);
    }
}
