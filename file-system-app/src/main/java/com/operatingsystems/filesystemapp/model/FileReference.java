package com.operatingsystems.filesystemapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Builder
@Data
@NoArgsConstructor(staticName = "instance")
@AllArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class FileReference {

    @JsonProperty("fileId")
    private String fileId;

    @JsonProperty("ownerUsername")
    private String ownerUsername;
}
