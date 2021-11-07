package com.operatingsystems.filesystemapp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.*;

@Builder
@Data
@NoArgsConstructor(staticName = "instance")
@AllArgsConstructor(staticName = "of")
@XmlRootElement(name = "Drive")
@XmlAccessorType(XmlAccessType.FIELD)
@Accessors(chain = true)
public class Drive {

    @XmlAttribute(name = "ownerName")
    private String ownerName;

    @XmlAttribute(name = "rootDir")
    private Directory rootDir;

    @XmlAttribute(name = "path")
    private String path;
}
