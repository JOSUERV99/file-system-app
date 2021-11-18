package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.Directory;

public interface DirectoryService {

    Directory getDirectory(final String username);

    Directory createDirectory(final String username);

    ActionResult createVirtualDirectory(final String username, final String dirId, final Directory newDir);

    // TODO : add all functions related to directories
}
