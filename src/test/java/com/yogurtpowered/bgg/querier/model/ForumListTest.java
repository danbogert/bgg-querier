package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.Lists;
import com.yogurtpowered.bgg.querier.ForumListBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class ForumListTest {
    private static String testResponse;
    private static XmlMapper mapper;

    @BeforeClass
    public static void setUpBeforeClass() throws IOException {
        testResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/forumlist.xml")));
        mapper = new XmlMapper();
    }

    @Test
    public void testParse() throws IOException {
        final ForumList forumList = mapper.readValue(testResponse, ForumList.class);

        assertEquals(50, forumList.getId());
        assertEquals(ForumListBuilder.Type.thing, forumList.getType());
        assertEquals("https://boardgamegeek.com/xmlapi/termsofuse", forumList.getTermsOfUse());

        List<Forum> forums = forumList.getForum();
        assertEquals(2, forums.size());

        verifyForum(forumList.getForum().get(0),
                1103,
                0,
                "Reviews",
                false,
                "Post your game reviews in this forum.",
                138,
                773,
                "Sat, 09 Jun 2018 03:22:32 +0000");
        verifyForum(forumList.getForum().get(1),
                814,
                0,
                "Sessions",
                false,
                "Post your session reports here.",
                228,
                509,
                "Wed, 30 May 2018 14:48:15 +0000");
    }

    private void verifyForum(Forum forum, int id, int groupId, String title, boolean noPosting, String description, int numThreads, int numPosts, String lastPostDate) {
        assertEquals(id, forum.getId());
        assertEquals(groupId, forum.getGroupid());
        assertEquals(title, forum.getTitle());
        assertEquals(noPosting, forum.isNoposting());
        assertEquals(description, forum.getDescription());
        assertEquals(numThreads, forum.getNumberOfThreads());
        assertEquals(numPosts, forum.getNumberOfPosts());
        assertEquals(lastPostDate, forum.getLastPostDate());
    }

    @Test
    public void serializeForums() throws IOException {
        final ForumList forumList = new ForumList();
        forumList.setId(1);
        forumList.setType(ForumListBuilder.Type.family);

        Forum forum = new Forum();
        forum.setDescription("description");
        forum.setGroupid(0);
        forum.setId(1);
        forum.setLastPostDate("last post date");
        forum.setNoposting(1);
        forum.setNumberOfPosts(2);
        forum.setNumberOfThreads(3);
        forum.setTitle("title");
        forumList.setForum(Lists.newArrayList(forum));


        OutputStream os = new ByteArrayOutputStream();
        mapper.writeValue(os, forumList);
        System.out.println(((ByteArrayOutputStream) os).toString("UTF-8"));
    }
}
