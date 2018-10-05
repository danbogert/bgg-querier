package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class ThingBuilder extends BggQuerier<String> {

    public enum ThingType { boardgame, boardgameaccessory, boardgameexpansion, rpgissue, rpgitem, videogame }

    private static final String THING_PATH = "thing";

    private final List<Integer> ids;
    private List<ThingType> thingTypes;
    private Boolean versions;
    private Boolean videos;
    private Boolean stats;
    private Boolean historical;
    private Boolean marketplace;
    private Boolean comments;
    private Boolean ratingComments;
    private Integer page;
    private Integer pageSize;

    ThingBuilder(int id) {
        Preconditions.checkArgument(id > 0);
        this.ids = Lists.newArrayList(id);
    }

    ThingBuilder(List<Integer> ids) {
        Preconditions.checkNotNull(ids);
        this.ids = ids;
    }

    /**
     * Specifies the id of the thing(s) to retrieve.
     */
    public ThingBuilder addId(int id) {
        Preconditions.checkArgument(id > 0);
        ids.add(id);
        return this;
    }

    /**
     * Filters the things asked for by the type(s) specified.
     */
    public ThingBuilder addThingType(ThingType thingType) {
        Preconditions.checkNotNull(thingType);
        if (thingTypes == null) {
            thingTypes = Lists.newArrayList();
        }
        thingTypes.add(thingType);
        return this;
    }

    /**
     * Returns version info for the item.
     */
    public ThingBuilder versions() {
        versions = true;
        return this;
    }

    /**
     * Returns videos for the item.
     */
    public ThingBuilder videos() {
        videos = true;
        return this;
    }

    /**
     * Returns ranking and rating stats for the item.
     */
    public ThingBuilder stats() {
        stats = true;
        return this;
    }

    /**
     * Returns historical data over time. See page parameter.
     */
    public ThingBuilder historical() {
        historical = true;
        return this;
    }

    /**
     * Returns marketplace data.
     */
    public ThingBuilder marketplace() {
        marketplace = true;
        return this;
    }

    /**
     * Returns all comments about the item. Also includes ratings when commented. See page parameter.
     */
    public ThingBuilder comments() {
        comments = true;
        return this;
    }

    /**
     * Returns all ratings for the item. Also includes comments when rated. See page parameter. The ratingcomments and
     * comments parameters cannot be used together, as the output always appears in the <comments> node of the XML;
     * comments parameter takes precedence if both are specified. Ratings are sorted in descending rating value, based
     * on the highest rating they have assigned to that item (each item in the collection can have a different rating).
     */
    public ThingBuilder ratingComments() {
        ratingComments = comments == null ? true : null;
        return this;
    }

    /**
     * Defaults to 1, controls the page of data to see for historical info, comments, and ratings data.
     */
    public ThingBuilder page(int page) {
        Preconditions.checkArgument(page > 0);
        this.page = page;
        return this;
    }

    /**
     * Set the number of records to return in paging. Minimum is 10, maximum is 100.
     */
    public ThingBuilder pageSize(int pageSize) {
        Preconditions.checkArgument(pageSize >= 10 && pageSize <= 100);
        this.pageSize = pageSize;
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(THING_PATH);

        addQueryParamIfSet(builder, "id", ids);
        addQueryParamIfSet(builder, "type", thingTypes);
        addQueryParamIfSet(builder, "versions", versions);
        addQueryParamIfSet(builder, "videos", videos);
        addQueryParamIfSet(builder, "stats", stats);
        addQueryParamIfSet(builder, "historical", historical);
        addQueryParamIfSet(builder, "marketplace", marketplace);
        addQueryParamIfSet(builder, "comments", comments);
        addQueryParamIfSet(builder, "ratingcomments", ratingComments);
        addQueryParamIfSet(builder, "page", page);
        addQueryParamIfSet(builder, "pagesize", pageSize);

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
