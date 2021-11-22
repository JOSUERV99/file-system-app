package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.handler.JSONUtils;
import com.operatingsystems.filesystemapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileSystemServiceImpl implements FileSystemService {


    @Override
    public String getFileContent(final String username, final String fileId) {
        return null;
    }

    @Override
    public ActionResult getFileProperties(String username, String fileId) {
        var drive = JSONUtils.getFullDrive(username);
        Object fileToReturn = searchFile(drive.getRootDir(),fileId);
        if(fileToReturn instanceof PlainTextFile) return ActionResult.instance().setSuccess(true).setObject(fileToReturn);
        return ActionResult.instance().setSuccess(false).setObject(fileToReturn);
    }

    @Override
    public ActionResult removeFile(final String username, final String fileId) {
        var drive = JSONUtils.getFullDrive(username);
        Object result = searchAndRemoveFile(drive.getRootDir(), fileId, null);
        if(result == null){
            return ActionResult.instance().setSuccess(false);
        }
        JSONUtils.saveDriveOrReplace(drive);
        return ActionResult.instance().setSuccess(true).setObject(result);
    }
    public Object searchAndRemoveFile(Directory dir, String fileId, Directory parentDir){
        if(dir.getId().equals(fileId)){
            parentDir.getChildrenDirectories().remove(dir);
            return dir;
        }
        for(PlainTextFile file : dir.getFiles()){
            if(file.getId().equals(fileId)){
                dir.getFiles().remove(file);
                return file;
            }
        }
        Object auxObject = null;
        for(Directory childDir : dir.getChildrenDirectories()){
            auxObject = searchAndRemoveFile(childDir, fileId, dir);
            if(auxObject != null){
                return auxObject;
            }
        }
        return auxObject;
    }

    @Override
    public Object getFile(final String username, final String fileId) {
        var drive = JSONUtils.getFullDrive(username);
        Object objectFile = searchFile(drive.getRootDir(), fileId);
        return objectFile;
    }
    @Override
    public Object searchFile(Directory dir, String fileId){
        if(dir.getId().equals(fileId)){
            return dir;
        }
        for(PlainTextFile file : dir.getFiles()){
            if(file.getId().equals(fileId)){
                return file;
            }
        }
        Object auxObject = null;
        for(Directory childDir : dir.getChildrenDirectories()){
            auxObject = searchFile(childDir, fileId);
            if(auxObject != null){
                return auxObject;
            }
        }
        return auxObject;
    }

    @Override
    public ActionResult copyFromVirtualToReal(String username, String fileId, String localPath) {
        var drive = JSONUtils.getFullDrive(username);
        Object fileorDirectory = searchFile(drive.getRootDir(), fileId);
        boolean success;
        if(fileorDirectory instanceof Directory){
            Directory dir = ((Directory) fileorDirectory);
            success = downloadFile(dir, localPath + drive.getName() + "/");
        }else{
            PlainTextFile file = ((PlainTextFile) fileorDirectory);
            try {
                createRealFile(file, localPath);
                success = true;
            } catch (IOException e) {
                e.printStackTrace();
                success = false;
            }
        }
        return ActionResult.instance().setSuccess(success).setObject(null);
    }

    @Override
    public ActionResult copyFromRealToVirtual(final String fileId, final String realDir, final String virtualDir) {
        return null;
    }

    @Override
    public ActionResult copyFromVirtualToVirtual(final String username, final String fileId, final String virtualDirDestination) {
        var drive = JSONUtils.getFullDrive(username);
        Directory newDir = ((Directory)searchFile(drive.getRootDir(), virtualDirDestination));
        Object fileToMove = searchFile(drive.getRootDir(), fileId);
        if(newDir != null && fileToMove != null){
            if(fileToMove instanceof Directory){
                newDir.getChildrenDirectories().add((Directory)fileToMove);
            }else if(fileToMove instanceof PlainTextFile){
                newDir.getFiles().add((PlainTextFile)fileToMove);
            }
            JSONUtils.saveDriveOrReplace(drive);
            return ActionResult.instance().setSuccess(true).setObject(newDir);
        }
        return ActionResult.instance().setSuccess(false).setObject(null);
    }

    @Override
    public ActionResult shareFile(final String fileId, final String buddyUserName, final String ownerUserName) {
        var drive = JSONUtils.getFullDrive(buddyUserName);
        FileReference newFileReference = FileReference.instance().setFileId(fileId).setOwnerUsername(ownerUserName);
        drive.getSharedReferences().add(newFileReference);
        JSONUtils.saveDriveOrReplace(drive);
        return ActionResult.instance().setSuccess(true).setObject(drive.getSharedReferences());
    }

    @Override
    public ActionResult createFile(final String username, final String dirId, final PlainTextFile newFile) {
        newFile.setId(UUID.randomUUID().toString());
        var drive = JSONUtils.getFullDrive(username);
        Object dirToInsert = searchFile(drive.getRootDir(), dirId);
        if (dirToInsert instanceof Directory){
            Directory directory = ((Directory) dirToInsert);
            directory.getFiles().add(newFile);
            JSONUtils.saveDriveOrReplace(drive);
            return ActionResult.instance().setSuccess(true).setObject(newFile);
        }
        return ActionResult.instance().setSuccess(false).setObject(dirToInsert);
    }

    @Override
    public ActionResult modifyFileContent(final String username, final String fileId, PlainTextFile newFileModified) {
        var drive = JSONUtils.getFullDrive(username);
        Object fileToChange = searchFile(drive.getRootDir(), fileId);
        if(fileToChange == null){
            fileToChange = searchFile(drive.getSharedWithMeDir(), fileId);
        }
        if (fileToChange instanceof PlainTextFile){
            ((PlainTextFile) fileToChange).setContent(newFileModified.getContent());
            JSONUtils.saveDriveOrReplace(drive);
            ((PlainTextFile) fileToChange).setModifiedDate(LocalDateTime.now().toString());
            updateOwnerFile(drive);
            return ActionResult.instance().setSuccess(true).setObject(fileToChange);
        }
        return ActionResult.instance().setSuccess(false).setObject(fileToChange);
    }

    public void updateOwnerFile(Drive drive){
        for(FileReference fileReference : drive.getSharedReferences()){
            var ownerDrive = JSONUtils.getFullDrive(fileReference.getOwnerUsername());
            Object fileToChange = searchFile(ownerDrive.getRootDir(), fileReference.getFileId());
            Object newFile = searchFile(drive.getSharedWithMeDir(), fileReference.getFileId());
            if(fileToChange != null && newFile != null) {
                if (fileToChange instanceof Directory) {
                    int idToReplace = ownerDrive.getRootDir().getChildrenDirectories().indexOf(fileToChange);
                    ownerDrive.getRootDir().getChildrenDirectories().set(idToReplace,(Directory) newFile);
                } else if (fileToChange instanceof PlainTextFile) {
                    int idToReplace = ownerDrive.getRootDir().getFiles().indexOf(fileToChange);
                    ownerDrive.getRootDir().getFiles().set(idToReplace,(PlainTextFile) newFile);
                }
                JSONUtils.saveDriveOrReplace(ownerDrive);
            }
        }

    }

    @Override
    public ActionResult moveFile(final String username, final String fileId, final String newDirId){
        var drive = JSONUtils.getFullDrive(username);
//        Directory oldDir = ((Directory)searchFile(drive.getRootDir(), oldDirId));
        Directory newDir = ((Directory)searchFile(drive.getRootDir(), newDirId));
        Object fileToMove = searchFile(drive.getRootDir(), fileId);
        if(newDir != null && fileToMove != null){
            if(fileToMove instanceof Directory){
                newDir.getChildrenDirectories().add((Directory)fileToMove);
                searchAndRemoveFile(drive.getRootDir(), fileId, null);
//                oldDir.getChildrenDirectories().remove((Directory)fileToMove);
            }else if(fileToMove instanceof PlainTextFile){
                newDir.getFiles().add((PlainTextFile)fileToMove);
                searchAndRemoveFile(drive.getRootDir(), fileId, null);
//                oldDir.getFiles().remove((PlainTextFile)fileToMove);
            }
            JSONUtils.saveDriveOrReplace(drive);
            return ActionResult.instance().setSuccess(true).setObject(newDir);
        }
        return ActionResult.instance().setSuccess(false).setObject(null);
    }

    @Override
    public boolean downloadFile(Directory dir, String path){
        for(PlainTextFile file : dir.getFiles()){
            //para todos los archivos dentro del directorio, creelos y guardelos
            try {
                createRealFile(file, path);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        for(Directory childDir : dir.getChildrenDirectories()){
            //para todos los directorios creelos y revise recursivamente dentro de ellos
            try {
                String childPath = path + "/" +childDir.getName();
                createRealDirectory(childPath);
                downloadFile(childDir, childPath);
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public void createRealFile(PlainTextFile file, String path) throws IOException{
        FileWriter myWriter = new FileWriter(path+"/"+file.getName()+"."+file.getExtension());
        myWriter.write(file.getContent());
        myWriter.close();
    }
    public void createRealDirectory(String path) throws IOException{
        Files.createDirectories(Paths.get(path));

    }
}
