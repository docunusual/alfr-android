package com.docunusual.alfr_android.eventfeed;

import com.docunusual.alfr_android.api.Event;

import java.util.ArrayList;
import java.util.List;


public class TestEventConsumer implements EventConsumer {

    private List<Event> events = new ArrayList<>();

    @Override
    public void add(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }
}
