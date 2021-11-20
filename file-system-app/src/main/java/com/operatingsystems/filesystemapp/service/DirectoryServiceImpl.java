package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import com.operatingsystems.filesystemapp.handler.FileUtils;
import com.operatingsystems.filesystemapp.handler.JSONUtils;
import com.operatingsystems.filesystemapp.handler.ModelUtils;
import com.operatingsystems.filesystemapp.model.ActionResult;
import com.operatingsystems.filesystemapp.model.Directory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Component(value = "directoryService")
public class DirectoryServiceImpl implements DirectoryService {

    private final FileSystemServiceImpl fileSystemServiceImpl;

    @Autowired
    public DirectoryServiceImpl(FileSystemServiceImpl fileSystemServiceImpl){
        this.fileSystemServiceImpl = fileSystemServiceImpl;
    }

    @Override
    public Directory getDirectory(String username) {

        String fileName = String.format("%s/%s.%s", FileSystemConstants.DEFAULT_DRIVES_LOCATION, username, FileSystemConstants.DEFAULT_DRIVE_EXTENSION);

        if (!FileUtils.fileExists(fileName)) return null;

        String jsonContent = FileUtils.getContentFromPlainTextFile(fileName);
        var mappedDir = JSONUtils.castJsonStringToHashMap(jsonContent);
        var dir = ModelUtils.mapToDirectoryObj(mappedDir);

        return dir;
    }

    @Override
    public Directory createDirectory(String username) {

        var fullPath = String.format("%s/%s.%s",FileSystemConstants.DEFAULT_DRIVES_LOCATION, username, FileSystemConstants.DEFAULT_DRIVE_EXTENSION);
        var dir = Directory.instance().setName(username).setFiles(List.of()).setChildrenDirectories(List.of()).setId(UUID.randomUUID().toString());

        // create the root dir for files
        String filesDir = String.format("%s/%s", FileSystemConstants.DEFAULT_FILE_SYSTEM_LOCATION, username);
        FileUtils.createDir(filesDir);

        return dir;
    }

    @Override
    public ActionResult createVirtualDirectory(final String username, final String dirName, final String dirId) {

        var newDir = Directory.instance().setId(UUID.randomUUID().toString()).setName(dirName);
        var drive = JSONUtils.getFullDrive(username);
        Object dirToInsert = fileSystemServiceImpl.searchFile(drive.getRootDir(), dirId);

        if (dirToInsert instanceof Directory){
            Directory directory = ((Directory) dirToInsert);
            directory.getChildrenDirectories().add(newDir);
            JSONUtils.saveDriveOrReplace(drive);
            return ActionResult.instance().setSuccess(true).setObject(newDir);
        }

        return ActionResult.instance().setSuccess(false).setObject(dirToInsert);
    };

}
