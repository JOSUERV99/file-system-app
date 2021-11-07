package com.operatingsystems.filesystemapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * ActionResult
 * Result for void actions, sending details about the transaction
 */
@Builder
@Data
@NoArgsConstructor(staticName = "instance")
@AllArgsConstructor(staticName = "of")
@Accessors(chain = true)
public class ActionResult {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("message")
    private String message;

}
