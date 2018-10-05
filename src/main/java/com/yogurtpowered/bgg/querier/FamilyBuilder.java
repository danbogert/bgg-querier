package com.yogurtpowered.bgg.querier;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public class FamilyBuilder extends BggQuerier<String> {

    public enum FamilyType { boardgamefamily, rpg, rpgperiodical }

    private static final String FAMILY_PATH = "family";

    private final List<Integer> ids;
    private List<FamilyType> familyTypes;

    FamilyBuilder(int id) {
        Preconditions.checkArgument(id > 0);
        this.ids = Lists.newArrayList(id);
    }

    FamilyBuilder(List<Integer> ids) {
        Preconditions.checkNotNull(ids);
        this.ids = ids;
    }

    /**
     * Specifies the id of the family to retrieve.
     */
    public FamilyBuilder addId(int id) {
        Preconditions.checkArgument(id > 0);
        ids.add(id);
        return this;
    }

    /**
     * Specifies that, regardless of the type of family asked for by id, the results are filtered by the family type(s)
     * specified.
     */
    public FamilyBuilder addFamilyType(FamilyType familyType) {
        Preconditions.checkNotNull(familyType);
        if (familyTypes == null) {
            familyTypes = Lists.newArrayList();
        }
        familyTypes.add(familyType);
        return this;
    }

    @Override
    protected String buildQueryUri() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(API_BASE_URL)
                .path(FAMILY_PATH);

        addQueryParamIfSet(builder, "id", ids);
        addQueryParamIfSet(builder, "type", familyTypes);

        return builder.toUriString();
    }

    @Override
    protected Class<String> getResponseType() {
        return String.class;
    }
}
