package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import com.operatingsystems.filesystemapp.handler.FileHandler;
import com.operatingsystems.filesystemapp.handler.FileUtils;
import com.operatingsystems.filesystemapp.handler.JSONUtils;
import com.operatingsystems.filesystemapp.handler.ModelUtils;
import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Component(value = "directoryService")
public class DirectoryServiceImpl implements DirectoryService {

    @Override
    public Directory getDirectory(String username) {

        String fileName = String.format("%s/%s.%s", FileSystemConstants.DEFAULT_DRIVES_LOCATION, username, FileSystemConstants.DEFAULT_DRIVE_EXTENSION);

//        System.out.println(fileName);
        if (!FileUtils.fileExists(fileName)) return null;

        String jsonContent = FileHandler.getContentFromPlainTextFile(fileName);
        var mappedDir = JSONUtils.castJsonStringToHashMap(jsonContent);
        var dir = ModelUtils.mapToDirectoryObj(mappedDir);

//        System.out.println(dir);

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


}
