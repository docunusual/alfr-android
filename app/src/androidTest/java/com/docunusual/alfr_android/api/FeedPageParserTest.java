package com.docunusual.alfr_android.api;

import com.docunusual.alfr_android.TestData;
import com.google.gson.Gson;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.net.URI;


public class FeedPageParserTest extends TestCase {

    public void testParseEmptyFeedPage() throws Exception {
        final FeedPage feedPage = new Gson().fromJson("{}", FeedPage.class);
        Assert.assertNotNull(feedPage);
    }

    public void testParseFeedPage() {
        final String feedPageJson = TestData.page("http://next.com/2", TestData.event("Tommy", "Hey Jim!"));
        final FeedPage feedPage = new Gson().fromJson(feedPageJson, FeedPage.class);
        Assert.assertEquals(URI.create("http://next.com/2"), feedPage.getNextHref());
        Assert.assertEquals(1, feedPage.getEvents().size());
        Assert.assertEquals("Tommy", feedPage.getEvents().get(0).getBy());
    }
}
