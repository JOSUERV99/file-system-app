package com.operatingsystems.filesystemapp.service;

import com.operatingsystems.filesystemapp.model.Directory;
import com.operatingsystems.filesystemapp.model.PlainTextFile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Component(value = "directoryService")
public class DirectoryServiceImpl implements DirectoryService {

    @Override
    public Directory getDirectory(String username) {

        // TESTING
        // TODO: delete this testing content when we implement this function
        var files =
                Arrays.asList(
                        PlainTextFile.instance()
                                .setName("helloWorld1")
                                .setBytesSize(1024L)
                                .setParentDir("root"),

                        PlainTextFile.instance()
                                .setName("helloWorld2")
                                .setBytesSize(2028L)
                                .setParentDir("root"),

                        PlainTextFile.instance()
                                .setName("helloWorld3")
                                .setBytesSize(4096L)
                                .setParentDir("root")
                );

        var directories =
                Arrays.asList(
                        Directory.instance().setName("dir1"),
                        Directory.instance().setName("dir2"),
                        Directory.instance().setName("dir3"),
                        Directory.instance().setName("dir4").setFiles(files));

        return Directory.instance().setFiles(files).setChildDirectory(directories).setName(username);
    }
}
