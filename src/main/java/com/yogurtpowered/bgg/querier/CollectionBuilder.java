package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import com.yogurtpowered.bgg.querier.model.Items;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class CollectionBuilder extends BggQuerier<Items> {

    public enum Subtype { boardgame, boardgameexpansion, boardgameaccessory, rpgitem, rpgissue, videogame }

    private static final String COLLECTION_PATH = "collection";

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
    private Boolean preordered;
    private Boolean wantToPlay;
    private Boolean wantToBuy;
    private Boolean previouslyOwned;
    private Boolean hasParts;
    private Boolean wantParts;
    private Boolean showPrivate;
    private Integer wishListPriority;
    private Integer minimumRating;
    private Integer maximumRating;
    private Integer minimumBggRating;
    private Integer maximumBggRating;
    private Integer minimumPlays;
    private Integer maximumPlays;
    private Integer collectionId;
    private String modifiedSince;

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

    /**
     * Filter for pre-ordered games. Set to true to include only pre-ordered items.
     */
    public CollectionBuilder preordered(boolean preordered) {
        this.preordered = preordered;
        return this;
    }

    /**
     * Filter for items marked as wanting to play. Set to false to exclude these items, set to true to include only these items.
     */
    public CollectionBuilder wantToPlay(boolean wantToPlay) {
        this.wantToPlay = wantToPlay;
        return this;
    }

    /**
     * Filter for items marked as wanting to buy. Set to false to exclude these items, set to true to include only these items.
     */
    public CollectionBuilder wantToBuy(boolean wantToBuy) {
        this.wantToBuy = wantToBuy;
        return this;
    }

    /**
     * Filter for games marked previously owned. Set to false to exclude these items, set to true to include only these items.
     */
    public CollectionBuilder previouslyOwned(boolean previouslyOwned) {
        this.previouslyOwned = previouslyOwned;
        return this;
    }

    /**
     * Filter on whether there is a comment in the Has Parts field of the item. Set to false to exclude these items, set to true to include only these items.
     */
    public CollectionBuilder hasParts(boolean hasParts) {
        this.hasParts = hasParts;
        return this;
    }

    /**
     * Filter on whether there is a comment in the Wants Parts field of the item. Set to false to exclude these items, set to true to include only these items.
     */
    public CollectionBuilder wantParts(boolean wantParts) {
        this.wantParts = wantParts;
        return this;
    }

    /**
     * Filter to show private collection info. Only works when viewing your own collection and you are logged in.
     */
    public CollectionBuilder showPrivate(boolean showPrivate) {
        this.showPrivate = showPrivate;
        return this;
    }

    /**
     * Filter for wishlist priority. Returns only items of the specified priority.
     * @param wishListPriority must be a value from 1-5
     */
    public CollectionBuilder wishListPriority(int wishListPriority) {
        Preconditions.checkArgument(wishListPriority >= 1 && wishListPriority <= 5);
        this.wishListPriority = wishListPriority;
        return this;
    }

    /**
     * Filter on minimum personal rating assigned for items in the collection.
     * @param minimumRating must be a value from 1-10
     */
    public CollectionBuilder minimumRating(int minimumRating) {
        Preconditions.checkArgument(minimumRating >= 1 && minimumRating <= 10);
        this.minimumRating = minimumRating;
        return this;
    }

    /**
     * Filter on maximum personal rating assigned for items in the collection.
     * @param maximumRating must be a value from 1-10
     */
    public CollectionBuilder maximumRating(int maximumRating) {
        Preconditions.checkArgument(maximumRating >= 1 && maximumRating <= 10);
        this.maximumRating = maximumRating;
        return this;
    }

    /**
     * Filter on minimum BGG rating for items in the collection.
     * @param minimumBggRating must be a value from 1-10 or -1 (to get items without a rating)
     */
    public CollectionBuilder minimumBggRating(int minimumBggRating) {
        Preconditions.checkArgument((minimumBggRating >= 1 && minimumBggRating <= 10) || (minimumBggRating == -1));
        this.minimumBggRating = minimumBggRating;
        return this;
    }

    /**
     * Filter on maximum BGG rating for items in the collection.
     * @param maximumBggRating must be a value from 1-10
     */
    public CollectionBuilder maximumBggRating(int maximumBggRating) {
        Preconditions.checkArgument(maximumBggRating >= 1 && maximumBggRating <= 10);
        this.maximumBggRating = maximumBggRating;
        return this;
    }

    /**
     * Filter by minimum number of recorded plays.
     * @param minimumPlays must be greater than or equal to zero and less than or equal to maximum plays, if set
     */
    public CollectionBuilder minmumPlays(int minimumPlays) {
        Preconditions.checkArgument(minimumPlays >= 0 && (maximumPlays == null || minimumPlays <= maximumPlays));
        this.minimumPlays = minimumPlays;
        return this;
    }

    /**
     * Filter by maximum number of recorded plays.
     * @param maximumPlays must be greater than or equal to zero and greater than or equal to minimum plays, if set
     */
    public CollectionBuilder maximumPlays(int maximumPlays) {
        Preconditions.checkArgument(maximumPlays >= 0 && (minimumPlays == null || minimumPlays <= maximumPlays));
        this.maximumPlays = maximumPlays;
        return this;
    }

    /**
     * Restrict the collection results to the single specified collection id. Collid is returned in the results of normal queries as well.
     */
    public CollectionBuilder collectionId(int collectionId) {
        this.collectionId = collectionId;
        return this;
    }

    /**
     * Restricts the collection results to only those whose status has changed since the date specified (does not return results for deletions).
     * @param year must be between 1990 and 2050
     * @param month must be between 1 and 12
     * @param day must be between 1 and 31
     */
    public CollectionBuilder modifiedSince(int year, int month, int day) {
        Preconditions.checkArgument(year >= 1990 && year <= 2050);
        Preconditions.checkArgument(month >= 1 && month <= 12);
        Preconditions.checkArgument(day >= 1 && day <= 31);
        this.modifiedSince = year % 100 + "-" + month + "-" + day;
        return this;
    }

    /**
     * Restricts the collection results to only those whose status has changed since the date specified (does not return results for deletions).
     * @param year must be between 1990 and 2050
     * @param month must be between 1 and 12
     * @param day must be between 1 and 31
     * @param hour must be between 0 and 23
     * @param minute must be between 0 and 59
     * @param second must be between 0 and 59
     * @return
     */
    public CollectionBuilder modifiedSince(int year, int month, int day, int hour, int minute, int second) {
        Preconditions.checkArgument(hour >= 0 && hour <= 23);
        Preconditions.checkArgument(minute >= 0 && minute <= 59);
        Preconditions.checkArgument(second >= 0 && second <= 59);
        modifiedSince(year, month, day);
        this.modifiedSince += " " + hour + ":" + minute + ":" + second;
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(COLLECTION_PATH)
                .queryParam("username", username);

        addQueryParamIfSet(builder, "version", retrieveVersion);
        addQueryParamIfSet(builder, "subtype", subtype == null ? null : subtype.toString());
        addQueryParamIfSet(builder, "excludesubtype", excludeSubtype == null ? null : excludeSubtype.toString());
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
        addQueryParamIfSet(builder, "preordered", preordered);
        addQueryParamIfSet(builder, "wanttoplay", wantToPlay);
        addQueryParamIfSet(builder, "wanttobuy", wantToBuy);
        addQueryParamIfSet(builder, "prevowned", previouslyOwned);
        addQueryParamIfSet(builder, "hasparts", hasParts);
        addQueryParamIfSet(builder, "wantparts", wantParts);
        addQueryParamIfSet(builder, "showprivate", showPrivate);
        addQueryParamIfSet(builder, "wishlistpriority", wishListPriority);
        addQueryParamIfSet(builder, "minrating", minimumRating);
        addQueryParamIfSet(builder, "rating", maximumRating);
        addQueryParamIfSet(builder, "minbggrating", minimumBggRating);
        addQueryParamIfSet(builder, "bggrating", maximumBggRating);
        addQueryParamIfSet(builder, "minplays", minimumPlays);
        addQueryParamIfSet(builder, "maxplays", maximumPlays);
        addQueryParamIfSet(builder, "collid", collectionId);
        addQueryParamIfSet(builder, "modifiedsince", modifiedSince);

        return builder.toUriString();
    }

    @Override
    protected Class<Items> getResponseType() {
        return Items.class;
    }
}
