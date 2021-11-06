package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Component(value = "fileService")
public class FileSystemServiceImpl implements FileSystemService {

    @Override
    public String getFileContent(String username, String fileId) {
        return null;
    }

    @Override
    public PlainTextFile getFileProperties(String username, String fileId) {
        // TESTING
        // TODO: delete this testing content when we implement this function
        var file =
                PlainTextFile.instance()
                        .setName("helloWorld")
                        .setBytesSize(1024L)
                        .setParentDir("root")
                        .setFileId(UUID.randomUUID());

        // UUID.fromString(fileId).equals(file.getName()) ? file : null;
        return file;
    }

    @Override
    public ActionResult removeFile(String username, String fileId) {
        return null;
    }

    @Override
    public ActionResult copyFromVirtualToReal(String fileId, String virtualDir, String realDir) {
        return null;
    }

    @Override
    public ActionResult copyFromRealToVirtual(String fileId, String realDir, String virtualDir) {
        return null;
    }

    @Override
    public ActionResult copyFromVirtualToVirtual(String fileId, String virtualDirOrigin, String virtualDirDestination) {
        return null;
    }

    @Override
    public ActionResult shareFile(String fileId, String buddyUserName) {
        return null;
    }

    @Override
    public ActionResult createFile(String fileId, String extension, String content) {
        return null;
    }

    @Override
    public ActionResult modifyFileContent(String fileId, String newContent) {
        return null;
    }
}
