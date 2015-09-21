package com.docunusual.alfr_android.api;

import java.util.List;

/**
 * Created by tnicolai on 21.09.15.
 */
public class Event {

    private String by;
    private List<Content> content;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
