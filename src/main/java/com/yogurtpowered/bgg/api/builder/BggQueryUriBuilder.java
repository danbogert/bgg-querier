package com.yogurtpowered.bgg.api.builder;

public abstract class BggQueryUriBuilder {
    protected static final String API_BASE_URL = "https://www.boardgamegeek.com/xmlapi2/";

    protected BggQueryUriBuilder() {
    }

    // thing
    // family
    // forumlist
    // forum
    // thread
    // user
    // guild
    // plays
    // collection
    public static CollectionBuilder collection(String username) {
        return new CollectionBuilder(username);
    }
    // hot
    // geeklist (not implemented)
    // search
}
