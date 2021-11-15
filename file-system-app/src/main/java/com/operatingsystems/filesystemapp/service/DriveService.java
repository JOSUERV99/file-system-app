package com.operatingsystems.filesystemapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.operatingsystems.filesystemapp.model.ActionResult;

public interface DriveService {

    ActionResult createDrive(final String driveName) throws JsonProcessingException;

    ActionResult getDrive(final String username);

    ActionResult walkToAnotherFolder(final String directoryName);

}
