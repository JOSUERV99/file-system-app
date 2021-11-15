package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.Directory;

public interface DirectoryService {

    Directory getDirectory(final String username);

    Directory createDirectory(final String username);

    // TODO : add all functions related to directories
}
