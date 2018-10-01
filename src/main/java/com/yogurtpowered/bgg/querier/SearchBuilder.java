package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchBuilder extends BggQuerier<String> {

    public enum Type { boardgame, boardgameaccessory, boardgameexpansion, rpgitem, videogame }

    private static final String SEARCH_PATH = "search";

    private final String query;
    private List<Type> types;
    private Boolean exact;

    SearchBuilder(String query) {
        Preconditions.checkArgument(query != null && query.length() > 0);
        this.query = query;
    }

    /**
     * Return all items that match the query of the given type(s).
     */
    public SearchBuilder type(Type... types) {
        Preconditions.checkArgument(types != null && types.length > 0);

        if (this.types == null) {
            this.types = new ArrayList<>();
        }

        this.types.addAll(Arrays.asList(types));
        return this;
    }

    /**
     * Limit results to items that match the query exactly
     */
    public SearchBuilder exact() {
        this.exact = Boolean.TRUE;
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(SEARCH_PATH)
                .queryParam("query", query);

        addQueryParamIfSet(builder, "type", types);
        addQueryParamIfSet(builder, "exact", exact);

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
