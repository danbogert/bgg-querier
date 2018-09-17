package com.yogurtpowered.bgg.api;

public enum CollectionType {
    BOARDGAME("boardgame"),
    VIDEOGAME("videogame");

    private final String type;

    CollectionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
