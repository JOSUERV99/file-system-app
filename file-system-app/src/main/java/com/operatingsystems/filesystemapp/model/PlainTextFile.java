package com.operatingsystems.filesystemapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.operatingsystems.filesystemapp.constants.FileSystemConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor(staticName = "instance")
@AllArgsConstructor(staticName = "of")
@XmlRootElement(name = "plain-text-file")
@XmlAccessorType(XmlAccessType.FIELD)
@Accessors(chain = true)
public class PlainTextFile {

    // to avoid filename detection we use a unique ID to find it without problems
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @XmlAttribute(name = "fileId")
    private UUID fileId;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "extension")
    @Builder.Default
    private String extension = FileSystemConstants.DEFAULT_FILE_EXTENSION;

    @XmlAttribute(name = "bytesSize")
    private Long bytesSize;

    @XmlElement(name = "createdDate")
    private Date createdDate;

    @XmlElement(name = "modifiedDate")
    private Date modifiedDate;

    @XmlAttribute(name = "parentDir")
    private String parentDir;
}