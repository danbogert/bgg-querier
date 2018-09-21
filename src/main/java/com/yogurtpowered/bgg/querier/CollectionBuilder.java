package com.yogurtpowered.bgg.querier;

import com.yogurtpowered.bgg.querier.model.Items;
import org.springframework.web.util.UriComponentsBuilder;

public class CollectionBuilder extends BggQuerier<Items> {

    public enum Subtype { boardgame, boardgameexpansion, boardgameaccessory, rpgitem, rpgissue, videogame }

    private static final String COLLECTION_PATH = "collection";

    private final String username;

    private boolean retrieveVersion;
    private Subtype subtype = null;
    private Subtype excludeSubtype = null;

    CollectionBuilder(String username) {
        this.username = username;
    }

    /**
     * Returns version info for each item in your collection.
     */
    public CollectionBuilder version() {
        this.retrieveVersion = true;
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

    @Override
    public Items query() {
        System.out.println(buildQueryUri());

        return restClient.getWithRetry(buildQueryUri(), Items.class);
    }

    String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(COLLECTION_PATH)
                .queryParam("username", username);

        if (retrieveVersion) {
            builder.queryParam("version", "1");
        }

        if (subtype != null) {
            builder.queryParam("subtype", subtype.toString());
        }

        if (excludeSubtype != null) {
            builder.queryParam("excludesubtype", excludeSubtype.toString());
        }

        return builder.toUriString();
    }
}

//id=NNN	Filter collection to specifically listed item(s). NNN may be a comma-delimited list of item ids.
//brief=1	Returns more abbreviated results.
//stats=1	Returns expanded rating/ranking info for the collection.
//own=[0,1]	Filter for owned games. Set to 0 to exclude these items so marked. Set to 1 for returning owned games and 0 for non-owned games.
//rated=[0,1]	Filter for whether an item has been rated. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//played=[0,1]	Filter for whether an item has been played. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//comment=[0,1]	Filter for items that have been commented. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//trade=[0,1]	Filter for items marked for trade. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//want=[0,1]	Filter for items wanted in trade. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
//wishlist=[0,1]	Filter for items on the wishlist. Set to 0 to exclude these items so marked. Set to 1 to include only these items so marked.
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