package com.yogurtpowered.bgg.querier;

import com.yogurtpowered.bgg.querier.model.Item;
import com.yogurtpowered.bgg.querier.model.Items;
import com.yogurtpowered.bgg.querier.utils.RestClient;

public abstract class BggQuerier<T> {
    protected static final String API_BASE_URL = "https://www.boardgamegeek.com/xmlapi2/";

    protected final RestClient restClient;

    protected BggQuerier() {
        this.restClient = new RestClient();
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

    public abstract T query();

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Pass the username as a argument");
        }

        Items items = BggQuerier.collection(args[0])
                .subtype(CollectionBuilder.Subtype.boardgame)
                .excludeSubtype(CollectionBuilder.Subtype.boardgameexpansion)
                .minmumPlays(8)
                .brief()
                .stats()
                .query();

        if (items != null && items.getItem() != null) {
            for (Item item : items.getItem()) {
                System.out.println(item);
            }
            System.out.println("Results: " + items.getItem().size());
        } else {
            System.out.println("Results: 0");
        }
    }
}
