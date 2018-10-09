package com.yogurtpowered.bgg.querier.model;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.Lists;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GuildTest {
    private static String testResponse;
    private static XmlMapper mapper;

    @BeforeClass
    public static void setUpBeforeClass() throws IOException {
        testResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/guild.xml")));
        mapper = new XmlMapper();
    }

    @Test
    public void testParse() throws IOException {
        final Guild guild = mapper.readValue(testResponse, Guild.class);

        assertEquals(1, guild.getId());
        assertEquals("Dallas Metrogamers", guild.getName());
        assertEquals("Thu, 14 Jun 2007 00:03:58 +0000", guild.getCreated());
        assertEquals("https://boardgamegeek.com/xmlapi/termsofuse", guild.getTermsOfUse());
        assertEquals("group", guild.getCategory());
        assertEquals("", guild.getWebsite());
        assertEquals("Aldie", guild.getManager());
        assertEquals("This is a guild for the Dallas Metrogamers.", guild.getDescription());

        Location location = guild.getLocation();
        assertNotNull(location);
        assertEquals("", location.getAddress1());
        assertEquals("", location.getAddress2());
        assertEquals("Dallas", location.getCity());
        assertEquals("Texas", location.getStateOrProvince());
        assertEquals("75218", location.getPostalCode());
        assertEquals("United States", location.getCountry());

        Members members = guild.getMembers();
        assertNotNull(members);
        assertEquals(3, members.getCount());
        assertEquals(1, members.getPage());
        assertEquals("Aldie", members.getMember().get(0).getName());
        assertEquals("Thu, 14 Jun 2007 00:03:58 +0000", members.getMember().get(0).getDate());
        assertEquals("andrews777", members.getMember().get(1).getName());
        assertEquals("Thu, 20 Dec 2007 23:54:24 +0000", members.getMember().get(1).getDate());
        assertEquals("apacolypse", members.getMember().get(2).getName());
        assertEquals("Fri, 22 Jan 2010 20:06:42 +0000", members.getMember().get(2).getDate());
    }


    @Test
    public void serializeGuild() throws IOException {
        final Guild guild = new Guild();
        guild.setId(1);
        guild.setCategory("podcast");
        guild.setCreated("date");
        Location location = new Location();
        location.setAddress1("ad1");
        location.setCity("city");
        guild.setLocation(location);
        Member m1 = new Member();
        m1.setName("n1");
        Member m2 = new Member();
        m2.setName("n2");
        Members members = new Members();
        members.setMember(Lists.newArrayList(m1, m2));
        guild.setMembers(members);

        OutputStream os = new ByteArrayOutputStream();
        mapper.writeValue(os, guild);
        System.out.println(((ByteArrayOutputStream) os).toString("UTF-8"));
    }
}
