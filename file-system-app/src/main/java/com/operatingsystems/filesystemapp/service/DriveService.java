package com.operatingsystems.filesystemapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.PasswordWrapper;

public interface DriveService {

    ActionResult createDrive(final String drive, final String password) throws JsonProcessingException;

    ActionResult getDrive(final String username, final String password);

    ActionResult walkToAnotherFolder(final String directoryName);

}
