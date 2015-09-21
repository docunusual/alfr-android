package com.docunusual.alfr_android;

import com.docunusual.alfr_android.api.FeedPage;
import com.google.gson.Gson;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.net.URI;


public class FeedParserTest extends TestCase {

    public void testParseEmptyFeedPage() throws Exception {
        final FeedPage feedPage = new Gson().fromJson("{}", FeedPage.class);
        Assert.assertNotNull(feedPage);
    }

    public void testParseFeedPage() {
        final FeedPage feedPage = new Gson().fromJson(
                "{'nextHref':'http://next.com/2','events':[" + EventParserTest.EVENT_1 + "]}", FeedPage.class);
        Assert.assertEquals(URI.create("http://next.com/2"), feedPage.getNextHref());
        Assert.assertEquals(1, feedPage.getEvents().size());
    }
}
