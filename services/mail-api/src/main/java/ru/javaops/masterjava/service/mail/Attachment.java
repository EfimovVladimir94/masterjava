package ru.javaops.masterjava.service.mail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlMimeType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Attachment {
    @XmlAttribute
    protected String name;

    @XmlMimeType("application/octet-stream")
    private DataHandler data;
}
