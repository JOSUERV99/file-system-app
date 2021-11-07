package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.ActionResult;

public interface DriveService {

    ActionResult createDrive(final String username);

    ActionResult getDrive(final String username);

    ActionResult walkToAnotherFolder(final String directoryName);

}
