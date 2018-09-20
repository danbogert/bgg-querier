package com.yogurtpowered.bgg.api.builder;

import org.junit.Test;

import static org.junit.Assert.*;

public class CollectionBuilderTest {

    @Test
    public void testBuilder_noOptionalArgs() {
        // Test
        String uri = BggQueryUriBuilder.collection("test_user").build();

        // Verify
        assertEquals("https://www.boardgamegeek.com/xmlapi2/collection?username=test_user", uri);
    }

}