package com.docunusual.alfr_android;


import java.util.Arrays;

public class TestData {

    public static String event(String by, String text) {
        return String.format("{'by':'%s','inline':'%s'}", by, text);
    }

    public static String page(String nextHref, String... events) {
        return String.format("{'nextHref':'%s','events':%s}", nextHref, Arrays.toString(events));
    }
}
