package com.docunusual.alfr_android;

import com.docunusual.alfr_android.eventfeed.FeedPageReader;

import java.net.URI;
import java.util.HashMap;

public class TestFeedPageReader implements FeedPageReader {

    private HashMap<URI, String> feedPages;

    public TestFeedPageReader() {
        feedPages = new HashMap<>();
        feedPages.put(
                URI.create("http://alfr.net/feed"),
                TestData.page("http://alfr.net/1",
                        TestData.event("Jim", "Hi Tommy."),
                        TestData.event("Tommy", "He is blue, Jim!"),
                        TestData.event("Jim", "But outside, watch for his side...")
                ));
        feedPages.put(
                URI.create("http://alfr.net/1"),
                TestData.page(null,
                        TestData.event("Tommy", "Lets get going"),
                        TestData.event("Jim", "Jimmerabing")
                ));
    }

    @Override
    public String request(URI uri) {
        return feedPages.get(uri);
    }
}
