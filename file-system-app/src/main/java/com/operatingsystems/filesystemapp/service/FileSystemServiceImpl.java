package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.handler.JSONUtils;
import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class FileSystemServiceImpl implements FileSystemService {

    @Override
    public String getFileContent(final String username, final String fileId) {
        return null;
    }

    @Override
    public PlainTextFile getFileProperties(String username, String fileId) {
//        // TESTING
//        // TODO: delete this testing content when we implement this function
//        var file =
//                PlainTextFile.instance()
//                        .setName("helloWorld")
//                        .setBytesSize(1024L)
//                        .setId(UUID.randomUUID().toString());

        return null;
    }

    @Override
    public ActionResult removeFile(final String username, final String fileId) {
        var drive = JSONUtils.getFullDrive(username);
        System.out.println(drive);
        return null;
    }

    @Override
    public ActionResult copyFromVirtualToReal(final String fileId, final String virtualDir, final String realDir) {
        return null;
    }

    @Override
    public ActionResult copyFromRealToVirtual(final String fileId, final String realDir, final String virtualDir) {
        return null;
    }

    @Override
    public ActionResult copyFromVirtualToVirtual(final String fileId, final String virtualDirOrigin, final String virtualDirDestination) {
        return null;
    }

    @Override
    public ActionResult shareFile(final String fileId, final String buddyUserName) {
        return null;
    }

    @Override
    public ActionResult createFile(final PlainTextFile newFile) {
        newFile.setId(UUID.randomUUID().toString());
        return ActionResult.instance().setSuccess(true).setObject(newFile);
    }

    @Override
    public ActionResult modifyFileContent(final String fileId, final String newContent) {
        return null;
    }
}
