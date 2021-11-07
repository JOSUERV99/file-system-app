package com.operatingsystems.filesystemapp.handler;

import com.operatingsystems.filesystemapp.constants.FileSystemConstants;

import java.io.File;
import java.util.List;

public class FileUtils {

    public static void initializeFileSystemDirs() {
        List.of(new File(FileSystemConstants.DEFAULT_FILE_SYSTEM_LOCATION), new File(FileSystemConstants.DEFAULT_DRIVES_LOCATION)).stream().filter(dir -> !dir.exists()).forEach(File::mkdirs);
    }
}
