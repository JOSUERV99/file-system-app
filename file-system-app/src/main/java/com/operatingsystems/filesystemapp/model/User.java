package com.operatingsystems.filesystemapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor(staticName = "instance")
@AllArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class User {
    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    @Builder.Default
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("createdDate")
    @Builder.Default
    private String createdDate = LocalDateTime.now().toString();

}
