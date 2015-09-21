package com.docunusual.alfr_android.api;

import java.net.URI;
import java.util.List;

/**
 * Created by tnicolai on 21.09.15.
 */
public class FeedPage {
    private URI nextHref;
    private List<Event> events;

    public URI getNextHref() {
        return nextHref;
    }

    public void setNextHref(URI nextHref) {
        this.nextHref = nextHref;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
