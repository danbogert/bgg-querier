package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import org.springframework.web.util.UriComponentsBuilder;

public class ForumListBuilder extends BggQuerier<String> {

    public enum Type { family, thing }

    private static final String FORUM_LIST_PATH = "forumlist";

    private final int id;
    private final Type type;

    ForumListBuilder(int id, Type type) {
        Preconditions.checkArgument(id > 0);
        Preconditions.checkNotNull(type);
        this.id = id;
        this.type = type;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(FORUM_LIST_PATH);

        addQueryParamIfSet(builder, "id", id);
        addQueryParamIfSet(builder, "type", type.toString());

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
