package com.operatingsystems.filesystemapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import java.util.List;

@Builder
@Data
@NoArgsConstructor(staticName = "instance")
@AllArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class Directory  {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    @Builder.Default
    private String type = "directory";

    @JsonProperty("files")
    @Builder.Default
    private List<PlainTextFile> files = List.of();

    @JsonProperty("childrenDirectories")
    @Builder.Default
    private List<Directory> childrenDirectories = List.of();
}