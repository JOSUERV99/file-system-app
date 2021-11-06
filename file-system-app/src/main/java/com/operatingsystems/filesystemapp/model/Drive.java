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
@XmlRootElement(name = "drive")
@XmlAccessorType(XmlAccessType.FIELD)
@Accessors(chain = true)
public class Drive {

    @XmlAttribute(name = "ownerName")
    private String ownerName;

    @XmlElement(name = "rootDir")
    private Directory rootDir;
}
