package com.operatingsystems.filesystemapp.handler;

import ch.qos.logback.core.util.FileUtil;
import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * FileHandler class
 * File handler function related to manipulate plain text files in the local directory
 *
 * @see com.operatingsystems.filesystemapp.service.FileSystemServiceImpl where this code will be invoked later
 */
@Component(value = "fileHandler")
public class FileHandler {

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
