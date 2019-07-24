package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "description",
        "group"
})
public class Project {

    @XmlElement(namespace = "http://javaops.ru", required = true)
    protected String description;

    @XmlElement(name = "Group", namespace = "http://javaops.ru", required = true)
    protected List<Project.Group> group;

    @XmlAttribute(name = "name", required = true)
    protected String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Project.Group> getGroup() {
        if (group == null) {
            group = new ArrayList<Project.Group>();
        }
        return this.group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Group {

        @XmlAttribute(name = "name", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String name;

        @XmlAttribute(name = "type", required = true)
        protected GroupType type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public GroupType getType() {
            return type;
        }

        public void setType(GroupType type) {
            this.type = type;
        }
    }

}
