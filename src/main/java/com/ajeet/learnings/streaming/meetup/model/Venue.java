package com.ajeet.learnings.streaming.meetup.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public final class Venue implements Serializable {
    private final String name;
    private final String venue_id;

    private final double lon;
    private final double lat;

    public Venue(String m_name, String m_id, double m_lon, double m_lat) {
        this.name = m_name;
        this.venue_id = m_id;
        this.lon = m_lon;
        this.lat = m_lat;
    }

    @JsonCreator
    public static Venue createVenue(
            @JsonProperty("venue_name") String name,
            @JsonProperty("venue_id") String venue_id,
            @JsonProperty("lon") double lon,
            @JsonProperty("lat") double lat){

        return new Venue(name, venue_id, lat, lon);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return venue_id;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
