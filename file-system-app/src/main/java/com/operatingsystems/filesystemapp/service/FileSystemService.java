package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.PlainTextFile;

public interface FileSystemService {

    String getFileContent(final String username, final String fileId);

    PlainTextFile getFileProperties(final String username, final String fileId);

    ActionResult removeFile(final String username, final String fileId);

    ActionResult copyFromVirtualToReal(final String fileId, final String virtualDir, final String realDir);

    ActionResult copyFromRealToVirtual(final String fileId, final String realDir, final String virtualDir);

    ActionResult copyFromVirtualToVirtual(final String fileId, final String virtualDirOrigin, final String virtualDirDestination);

    ActionResult shareFile(final String fileId, final String buddyUserName);

    ActionResult createFile(final String fileId, final String extension, final String content);

    ActionResult modifyFileContent(final String fileId, final String newContent);

}