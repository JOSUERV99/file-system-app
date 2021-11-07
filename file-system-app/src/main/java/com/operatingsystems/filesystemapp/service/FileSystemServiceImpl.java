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
    public String getFileContent(final String username, final String fileId) {
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
    public ActionResult removeFile(final String username, final String fileId) {
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
    public ActionResult createFile(final String fileId, final String extension, final String content) {
        return null;
    }

    @Override
    public ActionResult modifyFileContent(final String fileId, final String newContent) {
        return null;
    }
}
