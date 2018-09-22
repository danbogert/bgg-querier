package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "items")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {
    @JacksonXmlProperty(isAttribute = true, localName = "totalitems")
    private int totalItems;
    @JacksonXmlProperty(isAttribute = true, localName = "termsofuse")
    private String termsOfUse;
    @JacksonXmlProperty(isAttribute = true, localName = "pubdate")
    private String publishDate;
    @JacksonXmlElementWrapper(useWrapping = false, localName = "item")
    private List<Item> item;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public String getTermsOfUse() {
        return termsOfUse;
    }

    public void setTermsOfUse(String termsOfUse) {
        this.termsOfUse = termsOfUse;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Items{" +
                "totalItems=" + totalItems +
                ", termsOfUse='" + termsOfUse + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", item=" + item +
                '}';
    }
}
