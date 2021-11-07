package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.ActionResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component(value = "driveService")
public class DriveServiceImpl implements DriveService {

    @Override
    public ActionResult createDrive(String username) {
        return null;
    }

    @Override
    public ActionResult getDrive(String username) {
        // TESTING
        // TODO: delete this testing content when we implement this function
        return ActionResult.instance().setActionAuthor(username).setSuccess(false).setMessage("Error trying to get your drive");
    }

    @Override
    public ActionResult walkToAnotherFolder(String directoryName) {
        return null;
    }
}
