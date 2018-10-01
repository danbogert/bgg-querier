package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PlaysBuilder extends BggQuerier<String> {

    public enum Type { thing, family }
    public enum Subtype { boardgame, boardgameaccessory, boardgameexpansion, rpgitem, videogame }

    private static final String PLAYS_PATH = "plays";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    private String username;
    private Integer id;
    private Type type;
    private String minimumDate;
    private String maximumDate;
    private Subtype subtype;
    private Integer page;

    public PlaysBuilder(String username) {
        Preconditions.checkNotNull(username);
        this.username = username;
    }

    public PlaysBuilder(int id, Type type) {
        Preconditions.checkNotNull(type);
        this.id = id;
        this.type = type;
    }

    /**
     * Returns only plays of the specified date or later.
     */
    public PlaysBuilder minimumDate(LocalDate minimumDate) {
        Preconditions.checkNotNull(minimumDate);
        this.minimumDate = minimumDate.format(FORMATTER);
        return this;
    }

    /**
     * Returns only plays of the specified date or earlier.
     */
    public PlaysBuilder maximumDate(LocalDate maximumDate) {
        Preconditions.checkNotNull(maximumDate);
        this.maximumDate = maximumDate.format(FORMATTER);
        return this;
    }

    /**
     * Limits play results to the specified TYPE; boardgame is the default.
     */
    public PlaysBuilder subtype(Subtype subtype) {
        this.subtype = subtype;
        return this;
    }

    /**
     * The page of information to request, each page contains 100 records.
     */
    public PlaysBuilder page(int page) {
        this.page = page;
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(PLAYS_PATH);

        addQueryParamIfSet(builder, "username", username);
        addQueryParamIfSet(builder, "id", id);
        addQueryParamIfSet(builder, "type", type == null ? null : type.toString());
        addQueryParamIfSet(builder, "mindate", minimumDate);
        addQueryParamIfSet(builder, "maxdate", maximumDate);
        addQueryParamIfSet(builder, "subtype", subtype == null ? null : subtype.toString());
        addQueryParamIfSet(builder, "page", page);

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
