package com.yogurtpowered.bgg.querier;

import com.google.common.base.Joiner;
import com.yogurtpowered.bgg.querier.model.Item;
import com.yogurtpowered.bgg.querier.model.Items;
import com.yogurtpowered.bgg.querier.utils.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public abstract class BggQuerier<T> {
    protected static final String API_BASE_URL = "https://www.boardgamegeek.com/xmlapi2/";
    private static final Joiner JOINER = Joiner.on(',').skipNulls();

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
    public static CollectionBuilder collection(String username) {
        return new CollectionBuilder(username);
    }

    public static HotItemsBuilder hotItems() {
        return new HotItemsBuilder();
    }
    // geeklist (not implemented)
    // search

    public T query() {
        System.out.println(buildQueryUri());

        return restClient.getWithRetry(buildQueryUri(), getResponseType());
    }

    protected abstract String buildQueryUri();

    protected abstract Class<T> getResponseType();

    protected void addQueryParamIfSet(UriComponentsBuilder builder, String name, Boolean value) {
        if (value != null) {
            builder.queryParam(name, value == Boolean.TRUE ? "1" : "0");
        }
    }

    protected void addQueryParamIfSet(UriComponentsBuilder builder, String name, List<String> values) {
        if (values != null && !values.isEmpty()) {
            builder.queryParam(name, JOINER.join(values));
        }
    }

    protected void addQueryParamIfSet(UriComponentsBuilder builder, String name, Integer value) {
        if (value != null) {
            builder.queryParam(name, value.intValue());
        }
    }

    protected void addQueryParamIfSet(UriComponentsBuilder builder, String name, String value) {
        if (value != null) {
            builder.queryParam(name, value);
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("Pass the username as a argument");
        }

//        Items items = BggQuerier.collection(args[0])
//                .subtype(CollectionBuilder.Subtype.boardgame)
//                .excludeSubtype(CollectionBuilder.Subtype.boardgameexpansion)
//                .modifiedSince(2018, 9, 13)
//                .brief()
//                .stats()
//                .query();
//
//        if (items != null && items.getItem() != null) {
//            for (Item item : items.getItem()) {
//                System.out.println(item);
//            }
//            System.out.println("Results: " + items.getItem().size());
//        } else {
//            System.out.println("Results: 0");
//        }

        String hot = BggQuerier.hotItems()
                .type(HotItemsBuilder.Type.boardgame)
                .query();

        System.out.println(hot);
    }
}
