package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "item")
public class Item {

    @JacksonXmlProperty(isAttribute = true, localName = "objecttype")
    private String objectType;
    @JacksonXmlProperty(isAttribute = true, localName = "objectid")
    private String objectId;
    @JacksonXmlProperty(isAttribute = true, localName = "subtype")
    private String subType;
    @JacksonXmlProperty(isAttribute = true, localName = "collid")
    private String collectionId;
    @JacksonXmlProperty(localName = "name")
    private Name name;
    @JacksonXmlProperty(localName = "yearpublished")
    private int yearPublished;
    @JacksonXmlProperty(localName = "image")
    private String image;
    @JacksonXmlProperty(localName = "thumbnail")
    private String thumbnail;
    @JacksonXmlProperty(localName = "status")
    private Status status;
    @JacksonXmlProperty(localName = "numplays")
    private int numberOfPlays;

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getNumberOfPlays() {
        return numberOfPlays;
    }

    public void setNumberOfPlays(int numberOfPlays) {
        this.numberOfPlays = numberOfPlays;
    }

    @Override
    public String toString() {
        return "Item{" +
                "objectType='" + objectType + '\'' +
                ", objectId='" + objectId + '\'' +
                ", subType='" + subType + '\'' +
                ", collectionId='" + collectionId + '\'' +
                ", name=" + name +
                ", yearPublished=" + yearPublished +
                ", image='" + image + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", status=" + status +
                ", numberOfPlays=" + numberOfPlays +
                '}';
    }
}
