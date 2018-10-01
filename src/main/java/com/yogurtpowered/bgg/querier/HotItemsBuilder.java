package com.yogurtpowered.bgg.querier;

import org.springframework.web.util.UriComponentsBuilder;

public class HotItemsBuilder extends BggQuerier<String> {

    public enum Type { boardgame, boardgamecompany, boardgameperson, rpg, rpgcompany, rpgperson, videogame, videogamecompany }

    private static final String HOT_ITEMS_PATH = "hot";

    private Type type;

    HotItemsBuilder() {}

    public HotItemsBuilder type(Type type) {
        this.type = type;
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(HOT_ITEMS_PATH);

        addQueryParamIfSet(builder, "type", type == null ? null : type.toString());

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
