package com.docunusual.alfr_android.eventfeed;

import junit.framework.TestCase;

import java.net.URI;


public class FeedTest extends TestCase {

    public void testReader() throws Exception {
        final TestEventConsumer eventConsumer = new TestEventConsumer();
        Feed.read(URI.create("http://alfr.net/feed"), new TestFeedPageReader(), eventConsumer);
        assertEquals(5, eventConsumer.getEvents().size());
        assertEquals("Jimmerabing", eventConsumer.getEvents().get(0).getContent().get(0).getInline());
        assertEquals("Hi Tommy.", eventConsumer.getEvents().get(4).getContent().get(0).getInline());
    }

}
