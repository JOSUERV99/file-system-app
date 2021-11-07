package com.operatingsystems.filesystemapp.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor(staticName = "instance")
@AllArgsConstructor(staticName = "of")
@XmlRootElement(name = "Directory")
@XmlAccessorType(XmlAccessType.FIELD)
@Accessors(chain = true)
public class Directory  {

    // to avoid directory detection we use a unique ID to find it without problems
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @XmlAttribute(name = "dirId")
    private UUID dirId;

    @XmlAttribute(name = "name")
    private String name;

    @XmlElementWrapper(name = "files")
    @XmlElement(name = "file")
    @Builder.Default
    private List<PlainTextFile> files = List.of();

    @XmlElementWrapper(name = "childDirectories")
    @XmlElement(name = "directory")
    @Builder.Default
    private List<Directory> childDirectory = List.of();
}