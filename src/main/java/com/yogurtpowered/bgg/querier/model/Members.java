package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "members")
public class Members {
    @JacksonXmlProperty(isAttribute = true, localName = "count")
    private int count;
    @JacksonXmlProperty(isAttribute = true, localName = "page")
    private int page;
    @JacksonXmlElementWrapper(useWrapping = false, localName = "member")
    private List<Member> member;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Member> getMember() {
        return member;
    }

    public void setMember(List<Member> member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Members{" +
                "count=" + count +
                ", page=" + page +
                ", member=" + member +
                '}';
    }
}
