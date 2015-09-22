package com.docunusual.alfr_android.eventfeed;

import com.docunusual.alfr_android.api.Event;

public interface EventConsumer {

    void add(Event event);

}
