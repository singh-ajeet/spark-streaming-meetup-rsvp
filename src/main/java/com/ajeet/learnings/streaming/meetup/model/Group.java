package com.ajeet.learnings.streaming.meetup.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public final class Group implements Serializable {
    private final String group_id;
    private final String group_name;
    private final GroupTopic[] group_topics;
    private final String group_city;
    private final String group_country;

    private final double group_lon;
    private final double group_lat;
    private final String group_urlname;
    private final String group_state;

    public Group(String group_id, String group_name, GroupTopic[] group_topics, String group_city, String group_country, double group_lon, double group_lat, String group_urlname, String group_state) {
        this.group_id = group_id;
        this.group_name = group_name;
        this.group_topics = group_topics;
        this.group_city = group_city;
        this.group_country = group_country;
        this.group_lon = group_lon;
        this.group_lat = group_lat;
        this.group_urlname = group_urlname;
        this.group_state = group_state;
    }

    @JsonCreator
    public static Group createGroup(
            @JsonProperty("group_id") String group_id,
            @JsonProperty("group_name") String group_name,
            @JsonProperty("group_topics") GroupTopic[] group_topics,
            @JsonProperty("group_city") String group_city,
            @JsonProperty("group_country") String group_country,
            @JsonProperty("group_lon") double group_lon,
            @JsonProperty("group_lat") double group_lat,
            @JsonProperty("group_urlname") String group_urlname,
            @JsonProperty("group_state") String group_state){

        return new Group(group_id, group_name, group_topics, group_city, group_country, group_lon, group_lat, group_urlname, group_state);
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public GroupTopic[] getGroup_topics() {
        return group_topics;
    }

    public String getGroup_city() {
        return group_city;
    }

    public String getGroup_country() {
        return group_country;
    }

    public double getGroup_lon() {
        return group_lon;
    }

    public double getGroup_lat() {
        return group_lat;
    }

    public String getGroup_urlname() {
        return group_urlname;
    }

    public String getGroup_state() {
        return group_state;
    }
}
