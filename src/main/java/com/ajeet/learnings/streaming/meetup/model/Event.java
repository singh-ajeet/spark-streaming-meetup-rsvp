package com.ajeet.learnings.streaming.meetup.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.net.URL;

public final class Event implements Serializable {
    private final String event_name;
    private final String event_id;
    private final long time;
    private final URL event_url;

    public Event(String event_name, String event_id, long time, URL event_url) {
        this.event_name = event_name;
        this.event_id = event_id;
        this.time = time;
        this.event_url = event_url;
    }

    @JsonCreator
    public static Event createEvent(
            @JsonProperty("event_name") String event_name,
            @JsonProperty("event_id") String event_id,
            @JsonProperty("time") long time,
            @JsonProperty("event_url") URL event_url){

        return new Event(event_name, event_id, time, event_url);
    }


    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_id() {
        return event_id;
    }

    public long getTime() {
        return time;
    }

    public URL getEvent_url() {
        return event_url;
    }
}
