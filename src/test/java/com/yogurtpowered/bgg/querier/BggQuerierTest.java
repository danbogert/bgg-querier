package com.yogurtpowered.bgg.querier;

import com.yogurtpowered.bgg.querier.model.ForumList;
import com.yogurtpowered.bgg.querier.model.Guild;
import com.yogurtpowered.bgg.querier.model.Item;
import com.yogurtpowered.bgg.querier.model.Items;
import org.junit.Test;

import java.time.LocalDate;

public class BggQuerierTest {
    private static final String USERNAME = "TomVasel";
    private static final String GAME = "pandemic";

    @Test
    public void testCollection() {
        Items items = BggQuerier.collection(USERNAME)
                .subtype(CollectionBuilder.Subtype.boardgame)
                .excludeSubtype(CollectionBuilder.Subtype.boardgameexpansion)
                .modifiedSince(2018, 10, 5)
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
        String query = BggQuerier.search(GAME)
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
        Guild guild = BggQuerier.guild(24)
                .members()
                .page(2)
                .query();

        System.out.println(guild);
    }

    @Test
    public void testUser() {
        String query = BggQuerier.user(USERNAME)
                .buddies()
                .guilds()
                .hot()
                .top()
                .query();

        System.out.println(query);
    }

    @Test
    public void testThread() {
        String query = BggQuerier.thread(1)
                .minimumArticleDate(LocalDate.of(2005, 4, 24))
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

    @Test
    public void testForumList() {
        ForumList forumList = BggQuerier.forumList(30, ForumListBuilder.Type.family)
                .query();

        System.out.println(forumList);
    }

    @Test
    public void testFamily() {
        String query = BggQuerier.family(30)
                .addFamilyType(FamilyBuilder.FamilyType.boardgamefamily)
                .query();

        System.out.println(query);
    }

    @Test
    public void testThing() {
        String query = BggQuerier.thing(235802)
                .stats()
                .videos()
                .comments()
                .historical()
                .marketplace()
                .versions()
                .query();

        System.out.println(query);
    }
}