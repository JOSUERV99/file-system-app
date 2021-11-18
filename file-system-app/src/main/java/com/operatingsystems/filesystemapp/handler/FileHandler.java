package com.operatingsystems.filesystemapp.handler;

import ch.qos.logback.core.util.FileUtil;
import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * FileHandler class
 * File handler function related to manipulate plain text files in the local directory
 *
 * @see com.operatingsystems.filesystemapp.service.FileSystemServiceImpl where this code will be invoked later
 */
@Component(value = "fileHandler")
public class FileHandler {

    public static String getContentFromPlainTextFile(String fileName) {

        List<String> lines = Collections.emptyList();

        try {
            lines =
                    Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.stream().collect(Collectors.joining("\n", "", "\n"));
    }

    static boolean deleteFile(String filePath) {
        return false;
    }

    static boolean moveFile(String filePath, String destinationFolder) {
        return false;
    }

    static boolean modifyContentOfPlainTextFile(String filePath, String newContent) {
        return false;
    }


}
