package com.docunusual.alfr_android;

import com.docunusual.alfr_android.api.Event;
import com.docunusual.alfr_android.eventfeed.EventConsumer;

import java.util.ArrayList;
import java.util.List;


class TestEventConsumer implements EventConsumer {

    private List<Event> events = new ArrayList<>();

    @Override
    public void add(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }
}
