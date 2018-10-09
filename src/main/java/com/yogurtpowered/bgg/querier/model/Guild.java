package com.yogurtpowered.bgg.querier.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "guild")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Guild {
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private int id;
    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;
    @JacksonXmlProperty(isAttribute = true, localName = "created")
    private String created;
    @JacksonXmlProperty(isAttribute = true, localName = "termsofuse")
    private String termsOfUse;
    @JacksonXmlProperty(localName = "category")
    private String category;
    @JacksonXmlProperty(localName = "website")
    private String website;
    @JacksonXmlProperty(localName = "manager")
    private String manager;
    @JacksonXmlProperty(localName = "description")
    private String description;
    @JacksonXmlProperty(localName = "location")
    private Location location;
    @JacksonXmlProperty(localName = "members")
    private Members members;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Members getMembers() {
        return members;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Guild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created='" + created + '\'' +
                ", termsOfUse='" + termsOfUse + '\'' +
                ", category='" + category + '\'' +
                ", website='" + website + '\'' +
                ", manager='" + manager + '\'' +
                ", description='" + description + '\'' +
                ", location=" + location +
                ", members=" + members +
                '}';
    }
}
