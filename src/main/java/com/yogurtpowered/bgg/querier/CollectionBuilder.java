package com.yogurtpowered.bgg.querier;

import com.google.common.base.Joiner;
import com.yogurtpowered.bgg.querier.model.Items;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class CollectionBuilder extends BggQuerier<Items> {

    public enum Subtype { boardgame, boardgameexpansion, boardgameaccessory, rpgitem, rpgissue, videogame }

    private static final String COLLECTION_PATH = "collection";
    private static final Joiner JOINER = Joiner.on(',').skipNulls();

    private final String username;

    private Boolean retrieveVersion;
    private Subtype subtype = null;
    private Subtype excludeSubtype = null;
    private List<String> ids = null;
    private Boolean brief;
    private Boolean stats;
    private Boolean owned;
    private Boolean rated;
    private Boolean played;
    private Boolean commented;
    private Boolean forTrade;
    private Boolean wanted;
    private Boolean wishlisted;

    CollectionBuilder(String username) {
        this.username = username;
    }

    /**
     * Returns version info for each item in your collection.
     */
    public CollectionBuilder version() {
        this.retrieveVersion = Boolean.TRUE;
        return this;
    }

    /**
     * Specifies which collection you want to retrieve. The default subtype is {@link Subtype#boardgame}.
     */
    public CollectionBuilder subtype(Subtype subtype) {
        this.subtype = subtype;
        return this;
    }

    /**
     * Specifies which subtype you want to exclude from the results.
     */
    public CollectionBuilder excludeSubtype(Subtype excludeSubtype) {
        this.excludeSubtype = excludeSubtype;
        return this;
    }

    /**
     * Filter collection to specifically listed item(s).
     */
    public CollectionBuilder ids(List<String> ids) {
        this.ids = ids;
        return this;
    }

    /**
     * Returns more abbreviated results.
     */
    public CollectionBuilder brief() {
        this.brief = Boolean.TRUE;
        return this;
    }

    /**
     * Returns expanded rating/ranking info for the collection.
     */
    public CollectionBuilder stats() {
        this.stats = Boolean.TRUE;
        return this;
    }

    /**
     * Filter for owned games. Set to true to return owned games and false for non-owned games.
     */
    public CollectionBuilder owned(boolean owned) {
        this.owned = owned;
        return this;
    }

    /**
     * Filter for whether an item has been rated. Set to true to include only items with a rating.
     */
    public CollectionBuilder rated(boolean rated) {
        this.rated = rated;
        return this;
    }

    /**
     * Filter for whether an item has been played. Set to true to include only items that have been played.
     */
    public CollectionBuilder played(boolean played) {
        this.played = played;
        return this;
    }

    /**
     * Filter for items that have been commented. Set to true to include only items that have a comment;
     */
    public CollectionBuilder commented(boolean commented) {
        this.commented = commented;
        return this;
    }

    /**
     * Filter for items marked for trade. Set to true to include only items marked for trade.
     */
    public CollectionBuilder forTrade(boolean forTrade) {
        this.forTrade = forTrade;
        return this;
    }

    /**
     * Filter for items wanted in trade. Set to true to include only items wanted in trade.
     */
    public CollectionBuilder wanted(boolean wanted) {
        this.wanted = wanted;
        return this;
    }

    /**
     * Filter for items on the wishlist. Set to true to include only items that have been wishlisted.
     */
    public CollectionBuilder wishlisted(boolean wishlisted) {
        this.wishlisted = wishlisted;
        return this;
    }

    @Override
    public Items query() {
        System.out.println(buildQueryUri());

        return restClient.getWithRetry(buildQueryUri(), Items.class);
    }

    String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(COLLECTION_PATH)
                .queryParam("username", username);

        addQueryParamIfSet(builder, "version", retrieveVersion);
        addQueryParamIfSet(builder, "subtype", subtype);
        addQueryParamIfSet(builder, "excludesubtype", excludeSubtype);
        addQueryParamIfSet(builder, "id", ids);
        addQueryParamIfSet(builder, "brief", brief);
        addQueryParamIfSet(builder, "stats", stats);
        addQueryParamIfSet(builder, "own", owned);
        addQueryParamIfSet(builder, "rated", rated);
        addQueryParamIfSet(builder, "played", played);
        addQueryParamIfSet(builder, "comment", commented);
        addQueryParamIfSet(builder, "trade", forTrade);
        addQueryParamIfSet(builder, "want", wanted);
        addQueryParamIfSet(builder, "wishlist", wishlisted);

        return builder.toUriString();
    }

    private void addQueryParamIfSet(UriComponentsBuilder builder, String name, Boolean value) {
        if (value != null) {
            builder.queryParam(name, value == Boolean.TRUE ? "1" : "0");
        }
    }

    private void addQueryParamIfSet(UriComponentsBuilder builder, String name, Subtype value) {
        if (value != null) {
            builder.queryParam(name, value.toString());
        }
    }

    private void addQueryParamIfSet(UriComponentsBuilder builder, String name, List<String> values) {
        if (values != null && !values.isEmpty()) {
            builder.queryParam(name, JOINER.join(values));
        }
    }
}

//wishlistpriority=[1-5]	Filter for wishlist priority. Returns only items of the specified priority.
//preordered=[0,1]	Filter for pre-ordered games Returns only items of the specified priority. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//wanttoplay=[0,1]	Filter for items marked as wanting to play. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//wanttobuy=[0,1]	Filter for ownership flag. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//prevowned=[0,1]	Filter for games marked previously owned. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//hasparts=[0,1]	Filter on whether there is a comment in the Has Parts field of the item. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//wantparts=[0,1]	Filter on whether there is a comment in the Wants Parts field of the item. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//minrating=[1-10]	Filter on minimum personal rating assigned for that item in the collection.
//rating=[1-10]	Filter on maximum personal rating assigned for that item in the collection. [Note: Although you'd expect it to be maxrating, it's rating.]
//minbggrating=[1-10]	Filter on minimum BGG rating for that item in the collection. Note: 0 is ignored... you can use -1 though, for example min -1 and max 1 to get items w/no bgg rating.
//bggrating=[1-10]	Filter on maximum BGG rating for that item in the collection. [Note: Although you'd expect it to be maxbggrating, it's bggrating.]
//minplays=NNN	Filter by minimum number of recorded plays.
//maxplays=NNN	Filter by maximum number of recorded plays. [Note: Although the two maxima parameters above lack the max part, this one really is maxplays.]
//showprivate=1	Filter to show private collection info. Only works when viewing your own collection and you are logged in.
//collid=NNN	Restrict the collection results to the single specified collection id. Collid is returned in the results of normal queries as well.
//modifiedsince=YY-MM-DD	Restricts the collection results to only those whose status (own, want, fortrade, etc.) has changed or been added since the date specified (does not return results for deletions). Time may be added as well: modifiedsince=YY-MM-DD%20HH:MM:SS