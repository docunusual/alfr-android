package com.docunusual.alfr_android.eventfeed;

import com.docunusual.alfr_android.api.Event;
import com.docunusual.alfr_android.api.FeedPage;
import com.google.gson.Gson;

import java.net.URI;
import java.util.LinkedList;
import java.util.ListIterator;

public class Feed {

    public static void read(URI feedUri, FeedPageReader feedPageReader, EventConsumer eventConsumer) {
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
            eventConsumer.add(iterator.previous());
        }
    }

}
