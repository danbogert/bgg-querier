package com.yogurtpowered.bgg.querier;

import org.junit.Test;

import static org.junit.Assert.*;

public class CollectionBuilderTest {

    @Test
    public void testBuilder_noOptionalArgs() {
        // Test
        String uri = BggQuerier.collection("test_user").buildQueryUri();

        // Verify
        assertEquals("https://www.boardgamegeek.com/xmlapi2/collection?username=test_user", uri);
    }

    @Test
    public void testBuilder_retrieveVersion() {
        // Test
        String uri = BggQuerier.collection("test_user").version().buildQueryUri();

        // Verify
        assertEquals("https://www.boardgamegeek.com/xmlapi2/collection?username=test_user&version=1", uri);
    }

}