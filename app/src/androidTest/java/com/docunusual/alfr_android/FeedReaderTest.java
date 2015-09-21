package com.docunusual.alfr_android;


import com.docunusual.alfr_android.api.Event;
import com.docunusual.alfr_android.api.FeedPage;
import com.google.gson.Gson;

import junit.framework.TestCase;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class FeedReaderTest extends TestCase {

    public void testReader() throws Exception {
        final EventRepository eventRepository = new EventRepository();
        readFeed(URI.create("http://alfr.net/feed"), new FeedPageReader(), eventRepository);
        assertEquals(5, eventRepository.getEvents().size());
        assertEquals("Jimmerabing", eventRepository.getEvents().get(0).getContent().get(0).getInline());
        assertEquals("Hi Tommy.", eventRepository.getEvents().get(4).getContent().get(0).getInline());
    }

    public void readFeed(URI feedUri, FeedPageReader feedPageReader, EventRepository eventRepository) {
        final LinkedList<Event> events = new LinkedList<>();

        FeedPage feedPage;
        URI uri = feedUri;

        do {
            feedPage = new Gson().fromJson(feedPageReader.request(uri), FeedPage.class);
            events.addAll(feedPage.getEvents());
            uri = feedPage.getNextHref();
        } while (uri != null);

        final ListIterator<Event> iterator = events.listIterator(events.size());
        while (iterator.hasPrevious()) {
            eventRepository.add(iterator.previous());
        }
    }

    class FeedPageReader {

        private HashMap<URI, String> feedPages;

        public FeedPageReader() {
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

        public String request(URI uri) {
            return feedPages.get(uri);
        }
    }

    class EventRepository {

        private List<Event> events = new ArrayList<>();

        public void add(Event event) {
            events.add(event);
        }

        public List<Event> getEvents() {
            return events;
        }
    }
}
