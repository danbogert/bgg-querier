package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import org.springframework.web.util.UriComponentsBuilder;

public class ForumBuilder extends BggQuerier<String> {

    private static final String FORUM_PATH = "forum";

    private final int id;
    private Integer page;

    ForumBuilder(int id) {
        Preconditions.checkArgument(id > 0);
        this.id = id;
    }

    /**
     * The page of the thread list to return; page size is 50. Threads in the thread list are sorted in order of most recent post.
     */
    public ForumBuilder page(int page) {
        Preconditions.checkArgument(page > 0);
        this.page = page;
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(FORUM_PATH);

        addQueryParamIfSet(builder, "id", id);
        addQueryParamIfSet(builder, "page", page);

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
