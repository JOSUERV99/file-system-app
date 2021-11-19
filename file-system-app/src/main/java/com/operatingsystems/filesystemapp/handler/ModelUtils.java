package com.operatingsystems.filesystemapp.handler;

import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.model.Drive;
import com.operatingsystems.filesystemapp.model.FileReference;
import com.operatingsystems.filesystemapp.model.PlainTextFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ModelUtils {

    public static FileReference mapToFileReferenceObj(Map<String, Object> mappedObj){
        var fileReference = FileReference.instance();
        fileReference.setFileId((String) mappedObj.get("fileId"));
        fileReference.setOwnerUsername((String) mappedObj.get("ownerUsername"));
        return fileReference;
    }
    public static Directory mapToDirectoryObj(Map<String, Object> mappedObj)
    {
        var directory = Directory.instance();

        directory.setName((String) mappedObj.get("name"));
        directory.setType((String) mappedObj.get("type"));
        directory.setId((String) mappedObj.get("id"));

        if (mappedObj.get("childrenDirectories") instanceof List<?>)
            directory.setChildrenDirectories( ((List<?>) mappedObj.get("childrenDirectories")).stream().map(element ->
            {
              if (element instanceof Map<?,?>)
              {
                var mappedElement = (Map<String, Object>) element;
                return mapToDirectoryObj(mappedElement);
              }
                return null;
            }).filter(element -> element != null).collect(Collectors.toList()));

        if (mappedObj.get("files") instanceof List<?>)
            directory.setFiles( ((List<?>) mappedObj.get("files")).stream().map(element ->
            {
                if (element instanceof Map<?,?>)
                {
                    var mappedElement = (Map<String, Object>) element;
                    return mapToPlainTextFileObj(mappedElement);
                }
                return null;
            }).filter(element -> element != null).collect(Collectors.toList()));

        return directory;
    }

    public static PlainTextFile mapToPlainTextFileObj(Map<String, Object> mappedObj)
    {
        var plainTextFile = PlainTextFile.instance();

        plainTextFile.setName((String) mappedObj.get("name"));
        plainTextFile.setBytesSize((String) mappedObj.get("bytesSize"));
        plainTextFile.setCreatedDate((String) mappedObj.get("createdDate"));
        plainTextFile.setExtension((String) mappedObj.get("extension"));
        plainTextFile.setId((String) mappedObj.get("id"));
        plainTextFile.setType((String) mappedObj.get("type"));
        plainTextFile.setContent((String) mappedObj.get("content"));

        return plainTextFile;
    }

    public static Drive mapToDrive(Map<String, Object> mappedObj)
    {
        var drive = Drive.instance();

        drive.setName((String) mappedObj.get("name"));
        drive.setPassword((String) mappedObj.get("password"));
        drive.setId((String) mappedObj.get("id"));
        drive.setCurrentDir((String) mappedObj.get("currentDir"));
        drive.setRootDir(  mapToDirectoryObj  ((Map<String,Object>) mappedObj.get("rootDir")));
        drive.setOwner((String) mappedObj.get("owner"));
        drive.setSharedWithMeDir(  mapToDirectoryObj  ((Map<String,Object>) mappedObj.get("sharedWithMeDir")));

        if (mappedObj.get("sharedReferences") instanceof List<?>)
            drive.setSharedReferences( ((List<?>) mappedObj.get("sharedReferences")).stream().map(element ->
            {
                if (element instanceof Map<?,?>)
                {
                    var mappedElement = (Map<String, Object>) element;
                    return mapToFileReferenceObj(mappedElement);
                }
                return null;
            }).filter(element -> element != null).collect(Collectors.toList()));

        return drive;
    }
}
