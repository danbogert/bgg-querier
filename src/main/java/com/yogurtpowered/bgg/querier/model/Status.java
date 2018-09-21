package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Status {

    @JacksonXmlProperty(isAttribute = true, localName = "own")
    private boolean owned;
    @JacksonXmlProperty(isAttribute = true, localName = "prevowned")
    private boolean previouslyOwned;
    @JacksonXmlProperty(isAttribute = true, localName = "fortrade")
    private boolean forTrade;
    @JacksonXmlProperty(isAttribute = true, localName = "want")
    private boolean want;
    @JacksonXmlProperty(isAttribute = true, localName = "wanttoplay")
    private boolean wantToPlay;
    @JacksonXmlProperty(isAttribute = true, localName = "wanttobuy")
    private boolean wantToBuy;
    @JacksonXmlProperty(isAttribute = true, localName = "wishlist")
    private boolean wishList;
    @JacksonXmlProperty(isAttribute = true, localName = "preordered")
    private boolean preOrdered;
    @JacksonXmlProperty(isAttribute = true, localName = "lastmodified")
    private String lastModified;

    public boolean getOwned() {
        return owned;
    }

    public void setOwned(int owned) {
        this.owned = owned != 0;
    }

    public boolean getPreviouslyOwned() {
        return previouslyOwned;
    }

    public void setPreviouslyOwned(int previouslyOwned) {
        this.previouslyOwned = previouslyOwned != 0;
    }

    public boolean getForTrade() {
        return forTrade;
    }

    public void setForTrade(int forTrade) {
        this.forTrade = forTrade != 0;
    }

    public boolean getWant() {
        return want;
    }

    public void setWant(int want) {
        this.want = want != 0;
    }

    public boolean getWantToPlay() {
        return wantToPlay;
    }

    public void setWantToPlay(int wantToPlay) {
        this.wantToPlay = wantToPlay != 0;
    }

    public boolean getWantToBuy() {
        return wantToBuy;
    }

    public void setWantToBuy(int wantToBuy) {
        this.wantToBuy = wantToBuy != 0;
    }

    public boolean getWishList() {
        return wishList;
    }

    public void setWishList(int wishList) {
        this.wishList = wishList != 0;
    }

    public boolean getPreOrdered() {
        return preOrdered;
    }

    public void setPreOrdered(int preOrdered) {
        this.preOrdered = preOrdered != 0;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return "Status{" +
                "owned=" + owned +
                ", previouslyOwned=" + previouslyOwned +
                ", forTrade=" + forTrade +
                ", want=" + want +
                ", wantToPlay=" + wantToPlay +
                ", wantToBuy=" + wantToBuy +
                ", wishList=" + wishList +
                ", preOrdered=" + preOrdered +
                ", lastModified='" + lastModified + '\'' +
                '}';
    }
}
