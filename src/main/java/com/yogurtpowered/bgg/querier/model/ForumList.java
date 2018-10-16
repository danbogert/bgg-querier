package com.yogurtpowered.bgg.querier.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.yogurtpowered.bgg.querier.ForumListBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "forums")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ForumList {
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private int id;
    @JacksonXmlProperty(isAttribute = true, localName = "type")
    private ForumListBuilder.Type type;
    @JacksonXmlProperty(isAttribute = true, localName = "termsofuse")
    private String termsOfUse;
    @JacksonXmlElementWrapper(useWrapping = false, localName = "forum")
    private List<Forum> forum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ForumListBuilder.Type getType() {
        return type;
    }

    public void setType(ForumListBuilder.Type type) {
        this.type = type;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public List<Forum> getForum() {
        return forum;
    }

    public void setForum(List<Forum> forum) {
        this.forum = forum;
    }

    @Override
    public String toString() {
        return "Forums{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", termsOfUse='" + termsOfUse + '\'' +
                ", forum=" + forum +
                '}';
    }
}
