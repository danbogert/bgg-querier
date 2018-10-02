package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import org.springframework.web.util.UriComponentsBuilder;

public class GuildBuilder extends BggQuerier<String> {

    public enum SortType { date, username }

    private static final String GUILD_PATH = "guild";

    private final int id;
    private Boolean members;
    private SortType sortType;
    private Integer page;

    GuildBuilder(int id) {
        Preconditions.checkNotNull(id);
        this.id = id;
    }

    /**
     * Include member roster in the results. Member list is paged and sorted.
     */
    public GuildBuilder members() {
        this.members = Boolean.TRUE;
        return this;
    }

    /**
     * Specifies how to sort the members list; default is username.
     */
    public GuildBuilder sort(SortType sortType) {
        this.sortType = sortType;
        return this;
    }

    /**
     * The page of the members list to return. Page size is 25.
     */
    public GuildBuilder page(int page) {
        Preconditions.checkArgument(page >= 0);
        this.page = page;
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(GUILD_PATH)
                .queryParam("id", id);

        addQueryParamIfSet(builder, "members", members);
        addQueryParamIfSet(builder, "sort", sortType == null ? null : sortType.toString());
        addQueryParamIfSet(builder, "page", page);

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
