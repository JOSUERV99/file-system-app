package com.operatingsystems.filesystemapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor(staticName = "instance")
@AllArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class PlainTextFile {

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    @Builder.Default
    private String type = "plainTextFile";

    @JsonProperty("name")
    private String name;

    @JsonProperty("extension")
    @Builder.Default
    private String extension = FileSystemConstants.DEFAULT_FILE_EXTENSION;

    @JsonProperty("bytesSize")
    private String bytesSize;

    @JsonProperty("createdDate")
    @Builder.Default
    private String createdDate = LocalDateTime.now().toString();

    @JsonProperty("modifiedDate")
    @Builder.Default
    private String modifiedDate = LocalDateTime.now().toString();

    @JsonProperty("content")
    @Builder.Default
    private String content = "";
}