package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


@XmlType(name = "groupType", namespace = "http://javaops.ru")
@XmlEnum
public enum GroupType {

    REGISTERING,
    CURRENT,
    FINISHED;

    public String value() {
        return name();
    }

    public static GroupType fromValue(String v) {
     return valueOf(v);
    }
}
