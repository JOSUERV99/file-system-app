package com.operatingsystems.filesystemapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import com.operatingsystems.filesystemapp.handler.FileHandler;
import com.operatingsystems.filesystemapp.handler.FileUtils;
import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.model.Drive;
import com.sun.xml.bind.v2.runtime.XMLSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component(value = "driveService")
public class DriveServiceImpl implements DriveService {

    /**
     * Create the drive, generate the xml file and instantiate an empty dir for the user
     * @param driveName the name of the drive, where the personal drive will be defined
     * @return ActionResult, with result of the success of the drive creation
     */
    @Override
    public ActionResult createDrive(final String driveName) {

        var filename = String.format("%s/%s.%s",FileSystemConstants.DEFAULT_DRIVES_LOCATION, driveName, FileSystemConstants.DEFAULT_DRIVE_EXTENSION);

        var dir = FileUtils.createBasicDir(driveName);
        var drive =
            Drive.instance()
                .setOwnerName(driveName)
                .setRootDir(dir).setPath(filename);

        FileUtils.createDir(String.format("%s/%s",FileSystemConstants.DEFAULT_DRIVES_LOCATION, driveName));

        var xmlContent = FileUtils.mapObjectToXMLString(drive);

        // create the drive in xml format in the drives folder
        var success = FileUtils.createFile(FileSystemConstants.DEFAULT_DRIVES_LOCATION, driveName, FileSystemConstants.DEFAULT_DRIVE_EXTENSION, xmlContent);

        return ActionResult.instance().setSuccess(success).setMessage(success ? xmlContent : "There is an error creating the drive");
    }

    @Override
    public ActionResult getDrive(String username) {
        // TESTING
        // TODO: delete this testing content when we implement this function
        return ActionResult.instance().setSuccess(false).setMessage("Error trying to get your drive");
    }

    @Override
    public ActionResult walkToAnotherFolder(String directoryName) {
        return null;
    }
}
