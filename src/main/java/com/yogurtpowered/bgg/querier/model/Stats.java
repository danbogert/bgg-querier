package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Stats {

    @JacksonXmlProperty(isAttribute = true, localName = "minplayers")
    private int minPlayers;
    @JacksonXmlProperty(isAttribute = true, localName = "maxplayers")
    private int maxPlayers;
    @JacksonXmlProperty(isAttribute = true, localName = "minplaytime")
    private int minPlayTime;
    @JacksonXmlProperty(isAttribute = true, localName = "maxplaytime")
    private int maxPlayTime;
    @JacksonXmlProperty(isAttribute = true, localName = "playingtime")
    private int playingTime;
    @JacksonXmlProperty(isAttribute = true, localName = "numowned")
    private int numOwned;
    @JacksonXmlProperty(localName = "rating")
    private Rating rating;

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayTime() {
        return minPlayTime;
    }

    public void setMinPlayTime(int minPlayTime) {
        this.minPlayTime = minPlayTime;
    }

    public int getMaxPlayTime() {
        return maxPlayTime;
    }

    public void setMaxPlayTime(int maxPlayTime) {
        this.maxPlayTime = maxPlayTime;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    public int getNumOwned() {
        return numOwned;
    }

    public void setNumOwned(int numOwned) {
        this.numOwned = numOwned;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "minPlayers=" + minPlayers +
                ", maxPlayers=" + maxPlayers +
                ", minPlayTime=" + minPlayTime +
                ", maxPlayTime=" + maxPlayTime +
                ", playingTime=" + playingTime +
                ", numOwned=" + numOwned +
                ", rating=" + rating +
                '}';
    }
}
