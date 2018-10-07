package com.yogurtpowered.bgg.querier;

import com.google.common.base.Joiner;
import com.yogurtpowered.bgg.querier.utils.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

public abstract class BggQuerier<T> {
    protected static final String API_BASE_URL = "https://www.boardgamegeek.com/xmlapi2/";
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
    protected static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendLiteral(' ')
            .append(DateTimeFormatter.ISO_LOCAL_TIME)
            .parseStrict()
            .toFormatter();
    private static final Joiner COMMA_JOINER = Joiner.on(',').skipNulls();

    protected final RestClient restClient;

    protected BggQuerier() {
        this.restClient = new RestClient();
    }

    public static ThingBuilder thing(int id) {
        return new ThingBuilder(id);
    }

    public static ThingBuilder thing(List<Integer> ids) {
        return new ThingBuilder(ids);
    }

    public static FamilyBuilder family(int id) {
        return new FamilyBuilder(id);
    }

    public static FamilyBuilder family(List<Integer> ids) {
        return new FamilyBuilder(ids);
    }

    public static ForumListBuilder forumList(int id, ForumListBuilder.Type type) {
        return new ForumListBuilder(id, type);
    }

    public static ForumBuilder forum(int id) {
        return new ForumBuilder(id);
    }

    public static ThreadBuilder thread(int id) {
        return new ThreadBuilder(id);
    }

    public static UserBuilder user(String name) {
        return new UserBuilder(name);
    }

    public static GuildBuilder guild(int id) {
        return new GuildBuilder(id);
    }

    public static PlaysBuilder plays(String username) {
        return new PlaysBuilder(username);
    }

    public static CollectionBuilder collection(String username) {
        return new CollectionBuilder(username);
    }

    public static HotItemsBuilder hotItems() {
        return new HotItemsBuilder();
    }

    public static SearchBuilder search(String query) {
        return new SearchBuilder(query);
    }

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

    protected void addQueryParamIfSet(UriComponentsBuilder builder, String name, List<?> values) {
        if (values != null && !values.isEmpty()) {
            builder.queryParam(name, COMMA_JOINER.join(values));
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
}
