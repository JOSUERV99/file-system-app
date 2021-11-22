package com.operatingsystems.filesystemapp.handler;

import com.operatingsystems.filesystemapp.constants.FileSystemConstants;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    public static void initializeFileSystemDirs() {
        List.of(new File(FileSystemConstants.DEFAULT_FILE_SYSTEM_LOCATION), new File(FileSystemConstants.DEFAULT_DRIVES_LOCATION)).stream().filter(dir -> !dir.exists()).forEach(File::mkdirs);
    }

    public static boolean createDir(String directoryPath) {
        var dir = new File(FileSystemConstants.DEFAULT_FILE_SYSTEM_LOCATION);
        return !dir.exists() ? dir.mkdirs() : false;
    }

    public static boolean createFile(String location, String filename, String extension, String strContent) {

        try {
            var writer = new FileWriter(String.format("%s/%s.%s", location, filename, extension));
            writer.write(strContent);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean fileExists(String filename) {
        return new File(filename).exists();
    }

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

    public static File[] getFiles(){
        File dir = new File(FileSystemConstants.DEFAULT_DRIVES_LOCATION);
        File[] files = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".json");
            }
        });
        return files;
    }
}
