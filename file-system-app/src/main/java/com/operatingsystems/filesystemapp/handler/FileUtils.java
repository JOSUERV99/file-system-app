package com.operatingsystems.filesystemapp.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.operatingsystems.filesystemapp.constants.FileSystemConstants;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtils {

    public static void initializeFileSystemDirs() {
        List.of(new File(FileSystemConstants.DEFAULT_FILE_SYSTEM_LOCATION), new File(FileSystemConstants.DEFAULT_DRIVES_LOCATION)).stream().filter(dir -> !dir.exists()).forEach(File::mkdirs);
    }

    public static boolean createDir(String directoryPath) {
        var dir = new File(FileSystemConstants.DEFAULT_FILE_SYSTEM_LOCATION);
        return !dir.exists() ? dir.mkdirs() : false;
    }

    public static boolean createFile(String location, String filename, String extension, String strContent) {

        var file = new File(String.format("%s/%s.%s", location, filename, extension));

        try {
            if (file.createNewFile()) {
                var writer = new FileWriter(String.format("%s/%s.%s", location, filename, extension));
                writer.write(strContent);
                writer.close();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static String mapObjectToXMLString(Object input) {
        JacksonXmlModule xmlModule = new JacksonXmlModule();
        xmlModule.setDefaultUseWrapper(false);
        ObjectMapper objectMapper = new XmlMapper(xmlModule);

        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectMapper.writeValueAsString(input);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static boolean fileExists(String filename) {
        return new File(filename).exists();
    }
}
