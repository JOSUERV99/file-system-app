package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.PlainTextFile;

public interface FileSystemService {

    public String getFileContent(final String username, final String fileId);

    public PlainTextFile getFileProperties(final String username, final String fileId);

    public ActionResult removeFile(final String username, final String fileId);

    public ActionResult copyFromVirtualToReal(final String fileId, final String virtualDir, final String realDir);

    public ActionResult copyFromRealToVirtual(final String fileId, final String realDir, final String virtualDir);

    public ActionResult copyFromVirtualToVirtual(final String fileId, final String virtualDirOrigin, final String virtualDirDestination);

    public ActionResult shareFile(final String fileId, final String buddyUserName);

    public ActionResult createFile(final String fileId, final String extension, final String content);

    public ActionResult modifyFileContent(final String fileId, final String newContent);

}