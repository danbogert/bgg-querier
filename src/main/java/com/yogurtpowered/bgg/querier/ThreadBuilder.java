package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ThreadBuilder extends BggQuerier<String> {

    private static final String THREAD_PATH = "thread";

    private final int id;
    private Integer minimumArticleId;
    private String minimumArticleDate;
    private Integer count;

    ThreadBuilder(int id) {
        this.id = id;
    }

    /**
     * Filters the results so that only articles with an equal or higher id will be returned.
     */
    public ThreadBuilder minimumArticleId(int minimumArticleId) {
        this.minimumArticleId = minimumArticleId;
        return this;
    }

    /**
     * Filters the results so that only articles on the specified date or later will be returned.
     */
    public ThreadBuilder minimumArticleDate(LocalDate minimumArticleDate) {
        Preconditions.checkNotNull(minimumArticleDate);
        this.minimumArticleDate = DATE_FORMATTER.format(minimumArticleDate);
        return this;
    }

    /**
     * Filters the results so that only articles on the specified date and time or later will be returned.
     */
    public ThreadBuilder minimumArticleDate(LocalDateTime minimumArticleDate) {
        Preconditions.checkNotNull(minimumArticleDate);
        // TODO: Investigate -- doesn't like the space once it is encoded
        this.minimumArticleDate = DATE_TIME_FORMATTER.format(minimumArticleDate);
        return this;
    }

    /**
     * Limits the number of articles returned to no more than count.
     */
    public ThreadBuilder count(int count) {
        Preconditions.checkArgument(count > 0);
        this.count = count;
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(THREAD_PATH);

        addQueryParamIfSet(builder, "id", id);
        addQueryParamIfSet(builder, "minarticleid", minimumArticleId);
        addQueryParamIfSet(builder, "minarticledate", minimumArticleDate);
        addQueryParamIfSet(builder, "count", count);

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
