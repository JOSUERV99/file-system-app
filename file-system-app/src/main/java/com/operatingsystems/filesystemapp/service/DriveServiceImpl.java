package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import com.operatingsystems.filesystemapp.handler.FileHandler;
import com.operatingsystems.filesystemapp.handler.FileUtils;
import com.operatingsystems.filesystemapp.handler.JSONUtils;
import com.operatingsystems.filesystemapp.handler.ModelUtils;
import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.model.Drive;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component(value = "driveService")
public class DriveServiceImpl implements DriveService {

    private final DirectoryServiceImpl directoryService;

    @Autowired
    public DriveServiceImpl(DirectoryServiceImpl directoryService)
    {
        this.directoryService = directoryService;
    }

    /**
     * Create the drive, generate the xml file and instantiate an empty dir for the user
     * @param driveName the name of the drive, where the personal drive will be defined
     * @return ActionResult, with result of the drive creation process
     */
    @Override
    public ActionResult createDrive(final String driveName) {

        var dir = directoryService.createDirectory(driveName);
        var drive = Drive.instance().setRootDir(dir).setName(driveName).setId(UUID.randomUUID().toString()).setOwner(driveName).setCurrentDir("/");

        String jsonContent = JSONUtils.mapObjectToJsonString(drive);

        String filename = String.format("%s/%s.%s", FileSystemConstants.DEFAULT_DRIVES_LOCATION,driveName, FileSystemConstants.DEFAULT_DRIVE_EXTENSION);

        if (FileUtils.fileExists(filename))
            return ActionResult.instance().setSuccess(false).setMessage("There's is an error creating the drive").setMetadata(filename);
        else {
            FileUtils.createFile(FileSystemConstants.DEFAULT_DRIVES_LOCATION, driveName, FileSystemConstants.DEFAULT_DRIVE_EXTENSION, jsonContent);
            return ActionResult.instance().setSuccess(true).setMetadata(drive.getId()).setObject(drive).setMessage(jsonContent);
        }
    }

    @Override
    public ActionResult getDrive(String username) {
        var drive = JSONUtils.getFullDrive(username);
        return ActionResult.instance().setSuccess(true).setObject(drive);
    }

    @Override
    public ActionResult walkToAnotherFolder(String directoryName) {
        return null;
    }
}
