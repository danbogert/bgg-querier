package com.yogurtpowered.bgg.querier;

import com.yogurtpowered.bgg.querier.model.Item;
import com.yogurtpowered.bgg.querier.model.Items;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BggQuerierTest {
    private static final String USERNAME = "username";

    @Test
    public void testCollection() {
        Items items = BggQuerier.collection(USERNAME)
                .subtype(CollectionBuilder.Subtype.boardgame)
                .excludeSubtype(CollectionBuilder.Subtype.boardgameexpansion)
                .modifiedSince(2018, 9, 13)
                .brief()
                .stats()
                .query();

        if (items != null && items.getItem() != null) {
            for (Item item : items.getItem()) {
                System.out.println(item);
            }
            System.out.println("Results: " + items.getItem().size());
        } else {
            System.out.println("Results: 0");
        }
    }

    @Test
    public void testHotItems() {
        String hot = BggQuerier.hotItems()
                .type(HotItemsBuilder.Type.boardgame)
                .query();

        System.out.println(hot);
    }

    @Test
    public void testSearch() {
        String query = BggQuerier.search("pandemic")
                .type(SearchBuilder.Type.boardgame)
                .query();

        System.out.println(query);
    }

    @Test
    public void testPlays() {
        String query = BggQuerier.plays(USERNAME)
                .subtype(PlaysBuilder.Subtype.boardgame)
                .query();

        System.out.println(query);
    }

    @Test
    public void testGuild() {
        String query = BggQuerier.guild(1)
                .members()
                .query();

        System.out.println(query);
    }

    @Test
    public void testUser() {
        String query = BggQuerier.user("rahdo")
                .buddies()
                .guilds()
                .query();

        System.out.println(query);
    }

    @Test
    public void testThread() {
        String query = BggQuerier.thread(1)
                .minimumArticleDate(LocalDateTime.of(2005, 4, 24, 1, 18, 36))
                .query();

        System.out.println(query);
    }

    @Test
    public void testForum() {
        String query = BggQuerier.forum(300)
                .page(1)
                .query();

        System.out.println(query);
    }
}