package com.operatingsystems.filesystemapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor(staticName = "instance")
@AllArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class Drive {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("owner")
    private String owner;

    @JsonProperty("currentDir")
    private String currentDir;

    @JsonProperty("rootDir")
    private Directory rootDir;

    @JsonProperty("sharedReferences")
    @Builder.Default
    private List<FileReference> sharedReferences = new ArrayList<>();

    @JsonProperty("sharedWithMeDir")
    private Directory sharedWithMeDir;
}