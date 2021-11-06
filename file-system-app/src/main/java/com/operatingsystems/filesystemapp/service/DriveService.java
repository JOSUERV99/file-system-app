package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.ActionResult;

public interface DriveService {

    public ActionResult createDrive(final String username);

    public ActionResult getDrive(final String username);

    public ActionResult walkToAnotherFolder(final String directoryName);

}
