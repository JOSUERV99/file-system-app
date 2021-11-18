package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import com.operatingsystems.filesystemapp.handler.FileHandler;
import com.operatingsystems.filesystemapp.handler.FileUtils;
import com.operatingsystems.filesystemapp.handler.JSONUtils;
import com.operatingsystems.filesystemapp.handler.ModelUtils;
import com.operatingsystems.filesystemapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Component(value = "driveService")
public class DriveServiceImpl implements DriveService {

    private final DirectoryServiceImpl directoryService;
    private final FileSystemServiceImpl fileSystemServiceImpl;

    @Autowired
    public DriveServiceImpl(DirectoryServiceImpl directoryService,FileSystemServiceImpl fileSystemServiceImpl) {
        this.directoryService = directoryService;
        this.fileSystemServiceImpl = fileSystemServiceImpl;
    }

    /**
     * Create the drive, generate the xml file and instantiate an empty dir for the user
     * @param driveName the name of the drive, where the personal drive will be defined
     * @return ActionResult, with result of the drive creation process
     */
    @Override
    public ActionResult createDrive(final String driveName, final String password) {

        var dir = directoryService.createDirectory(driveName);
        var sharedWithMeDir = directoryService.createDirectory("sharedWithMeDir");
        var drive = Drive.instance().setRootDir(dir).setName(driveName).setPassword(password).setId(UUID.randomUUID().toString()).setOwner(driveName).setCurrentDir("/").setSharedReferences(List.of()).setSharedWithMeDir(sharedWithMeDir);

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
    public ActionResult getDrive(String username, String password) {
        var drive = JSONUtils.getFullDrive(username);
        String validPassword = drive.getPassword();
        if (validPassword.equals(password)) {
            return ActionResult.instance().setMessage("Login Successful").setSuccess(true).setObject(drive);
        } else {
            return ActionResult.instance().setMessage("The password is not correct").setSuccess(false);
        }
    }

    @Override
    public ActionResult walkToAnotherFolder(String directoryName) {
        return null;
    }

    @Override
    public ActionResult getSharedFiles(final String username) {
        var drive = JSONUtils.getFullDrive(username);
        List<Directory> newChildrenDirectories = new ArrayList();
        List<PlainTextFile> newFiles = new ArrayList();
        for(FileReference fileReference : drive.getSharedReferences()){
            var ownerDrive = JSONUtils.getFullDrive(fileReference.getOwnerUsername());
            Object fileToShared = fileSystemServiceImpl.searchFile(ownerDrive.getRootDir(), fileReference.getFileId());
            if(fileToShared != null) {
                if (fileToShared instanceof Directory) {
                    newChildrenDirectories.add((Directory) fileToShared);
                } else if (fileToShared instanceof PlainTextFile) {
                    newFiles.add((PlainTextFile) fileToShared);
                }
            }
        }
        drive.getSharedWithMeDir().setFiles(newFiles).setChildrenDirectories(newChildrenDirectories);
        JSONUtils.saveDriveOrReplace(drive);
        return ActionResult.instance().setSuccess(true).setObject(drive.getSharedWithMeDir());
    }

}
