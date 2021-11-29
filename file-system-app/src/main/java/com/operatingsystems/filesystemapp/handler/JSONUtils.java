package com.operatingsystems.filesystemapp.handler;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import com.operatingsystems.filesystemapp.model.Drive;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JSONUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String mapObjectToJsonString(Object obj)
    {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Map<String, Object> castJsonStringToHashMap(String jsonString) {

        Map<String, Object> map = new HashMap<String, Object>();

        try {
            map = mapper.readValue(jsonString, new TypeReference<HashMap<String, Object>>(){});
        }
        catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Drive getFullDrive(String username) {
        String jsonContent = FileUtils.getContentFromPlainTextFile(getPathToDriveFile(username));
        var mappedDrive = JSONUtils.castJsonStringToHashMap(jsonContent);
        var drive = ModelUtils.mapToDrive(mappedDrive);

        return drive;
    }

    public static boolean saveDriveOrReplace(Drive drive) {
        String jsonContent = JSONUtils.mapObjectToJsonString(drive);
        boolean success = FileUtils.createFile(FileSystemConstants.DEFAULT_DRIVES_LOCATION, drive.getName(), FileSystemConstants.DEFAULT_DRIVE_EXTENSION, jsonContent);
        return success;
    }

    private static String getPathToDriveFile(String username) {
        return String.format("%s/%s.%s", FileSystemConstants.DEFAULT_DRIVES_LOCATION, username, FileSystemConstants.DEFAULT_DRIVE_EXTENSION);
    }
}
