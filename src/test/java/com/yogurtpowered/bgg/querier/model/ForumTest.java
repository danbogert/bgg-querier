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

public class ForumTest {
    private static String testResponse;
    private static XmlMapper mapper;

    @BeforeClass
    public static void setUpBeforeClass() throws IOException {
        testResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/forum.xml")));
        mapper = new XmlMapper();
    }

    @Test
    public void testParse() throws IOException {
        final Forum forum = mapper.readValue(testResponse, Forum.class);

        System.out.println(forum);

        verifyForum(forum,
                300,
                0,
                "General",
                false,
                null,
                2,
                178,
                "Thu, 01 Jan 1970 00:00:00 +0000");
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
