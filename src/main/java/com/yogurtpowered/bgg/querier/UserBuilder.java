package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import org.springframework.web.util.UriComponentsBuilder;

public class UserBuilder extends BggQuerier<String> {

    public enum Domain { boardgame, rpg, videogame }

    private static final String USER_PATH = "user";

    private final String name;
    private Boolean buddies;
    private Boolean guilds;
    private Boolean hot;
    private Boolean top;
    private Domain domain;
    private Integer page;

    UserBuilder(String name) {
        Preconditions.checkNotNull(name);
        this.name = name;
    }

    /**
     * Turns on optional buddies reporting. Results are paged; see page parameter.
     */
    public UserBuilder buddies() {
        buddies = true;
        return this;
    }

    /**
     * Turns on optional guilds reporting. Results are paged; see page parameter.
     */
    public UserBuilder guilds() {
        guilds = true;
        return this;
    }

    /**
     * Include the user's hot 10 list from their profile. Omitted if empty.
     */
    public UserBuilder hot() {
        hot = true;
        return this;
    }

    /**
     * Include the user's top 10 list from their profile. Omitted if empty.
     */
    public UserBuilder top() {
        top = true;
        return this;
    }

    /**
     * Controls the domain for the users hot 10 and top 10 lists. The default is boardgame.
     */
    public UserBuilder domain(Domain domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Specifies the page of buddy and guild results to return. The default page is 1 if you don't specify it; page size
     * is 100 records (Current implementation seems to return 1000 records). The page parameter controls paging for both
     * buddies and guilds list if both are specified. If a <buddies> or <guilds> node is empty, it means that you have
     * requested a page higher than that needed to list all the buddies/guilds or, if you're on page 1, it means that
     * that user has no buddies and is not part of any guilds.
     */
    public UserBuilder page(int page) {
        this.page = page;
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(USER_PATH)
                .queryParam("name", name);

        addQueryParamIfSet(builder, "buddies", buddies);
        addQueryParamIfSet(builder, "guilds", guilds);
        addQueryParamIfSet(builder, "hot", hot);
        addQueryParamIfSet(builder, "top", top);
        addQueryParamIfSet(builder, "domain", domain == null ? null : domain.toString());
        addQueryParamIfSet(builder, "page", page);

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
