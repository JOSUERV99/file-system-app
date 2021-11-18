package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface FileSystemService {

    String getFileContent(final String username, final String fileId);

    PlainTextFile getFileProperties(final String username, final String fileId);

    ActionResult removeFile(final String username, final String fileId);

    Object getFile(final String username, final String fileId);

    Object searchFile(Directory dir, String fileId);

    ActionResult copyFromVirtualToReal(final String fileId, final String virtualDir, final String realDir);

    ActionResult copyFromRealToVirtual(final String fileId, final String realDir, final String virtualDir);

    ActionResult copyFromVirtualToVirtual(final String fileId, final String virtualDirOrigin, final String virtualDirDestination);

    ActionResult shareFile(final String fileId, final String buddyUserName, final String ownerUserName);

    ActionResult getSharedFiles(final String username);

    ActionResult createFile(final String username, final String dirId, final PlainTextFile newFile);

    ActionResult modifyFileContent(final String username, final String fileId, PlainTextFile newFileModified);


}