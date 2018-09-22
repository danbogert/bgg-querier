package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "name")
public class Name {

    @JacksonXmlProperty(isAttribute = true, localName = "sortindex")
    private String sortIndex;
    @JacksonXmlText
    private String name;

    public String getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(String sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\'' +
                ", sortIndex='" + sortIndex + '\'' +
                '}';
    }
}
