package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "forum")
public class Forum {
    @JacksonXmlProperty(isAttribute = true, localName = "id")
    private int id;
    @JacksonXmlProperty(isAttribute = true, localName = "groupid")
    private int groupid;
    @JacksonXmlProperty(isAttribute = true, localName = "title")
    private String title;
    @JacksonXmlProperty(isAttribute = true, localName = "noposting")
    private boolean noposting;
    @JacksonXmlProperty(isAttribute = true, localName = "description")
    private String description;
    @JacksonXmlProperty(isAttribute = true, localName = "numthreads")
    private int numberOfThreads;
    @JacksonXmlProperty(isAttribute = true, localName = "numposts")
    private int numberOfPosts;
    @JacksonXmlProperty(isAttribute = true, localName = "lastpostdate")
    private String lastPostDate;


    // <forum id="20658"
    // groupid="0"
    // title="General"
    // noposting="0"
    // description="General discussion about this family of items"
    // numthreads="2"
    // numposts="17"
    // lastpostdate="Fri, 02 Dec 2011 16:59:11 +0000" />

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isNoposting() {
        return noposting;
    }

    public int getNoposting() {
        return noposting ? 1 : 0;
    }

    public void setNoposting(int noposting) {
        this.noposting = noposting == 1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }

    public int getNumberOfPosts() {
        return numberOfPosts;
    }

    public void setNumberOfPosts(int numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
    }

    public String getLastPostDate() {
        return lastPostDate;
    }

    public void setLastPostDate(String lastPostDate) {
        this.lastPostDate = lastPostDate;
    }

    @Override
    public String toString() {
        return "Forum{" +
                "id=" + id +
                ", groupid=" + groupid +
                ", title='" + title + '\'' +
                ", noposting=" + noposting +
                ", description='" + description + '\'' +
                ", numberOfThreads=" + numberOfThreads +
                ", numberOfPosts=" + numberOfPosts +
                ", lastPostDate=" + lastPostDate +
                '}';
    }
}
