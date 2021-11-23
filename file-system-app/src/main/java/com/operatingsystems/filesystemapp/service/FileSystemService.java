package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.model.Drive;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public interface FileSystemService {

    void updateOwnerFile(Drive drive);

    ActionResult getFileProperties(final String username, final String fileId);

    ActionResult removeFile(final String username, final String fileId);

    Object getFile(final String username, final String fileId);

    Object searchFile(Directory dir, String fileId);

    ActionResult copyFromVirtualToReal(String username, String fileId, String localPath);

    ActionResult copyFromRealToVirtual(final String fileId, final String realDir, final String virtualDir);

    ActionResult copyFromVirtualToVirtual(final String username, final String fileId, final String virtualDirDestination);

    ActionResult shareFile(final String fileId, final String buddyUserName, final String ownerUserName);

    ActionResult createFile(final String username, final String dirId, final PlainTextFile newFile);

    ActionResult modifyFileContent(final String username, final String fileId, PlainTextFile newFileModified);

    ActionResult moveFile(final String username, final String fileId, final String newDirId);



    boolean downloadFile(Directory dir, String path);

    void createRealFile(PlainTextFile file, String path) throws IOException;

    void createRealDirectory(String path) throws IOException;


}