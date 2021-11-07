package com.operatingsystems.filesystemapp.handler;

import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import org.springframework.stereotype.Component;

/**
 * FileHandler class
 * File handler function related to manipulate plain text files in the local directory
 *
 * @see com.operatingsystems.filesystemapp.service.FileSystemServiceImpl where this code will be invoked later
 */
@Component(value = "fileHandler")
public class FileHandler {

    private static final String LOCAL_ROOT_DIRECTORY = FileSystemConstants.DEFAULT_FILE_SYSTEM_LOCATION;

    String getContentFromPlainTextFile(String filePath) {
        return null;
    }

    boolean deleteFile(String filePath) {
        return false;
    }

    boolean moveFile(String filePath, String destinationFolder) {
        return false;
    }

    boolean modifyContentOfPlainTextFile(String filePath, String newContent) {
        return false;
    }

}