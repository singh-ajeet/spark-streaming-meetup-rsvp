package com.ajeet.learnings.streaming.meetup.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public final class GroupTopic implements Serializable {
    private final String urlkey;
    private final String topic_name;

    public GroupTopic(String urlkey, String topic_name) {
        this.urlkey = urlkey;
        this.topic_name = topic_name;
    }

    @JsonCreator
    public static GroupTopic createGroupTopic(
            @JsonProperty("urlkey") String urlkey,
            @JsonProperty("topic_name") String topic_name){

        return new GroupTopic(urlkey, topic_name);
    }

    public String getUrlkey() {
        return urlkey;
    }

    public String getTopic_name() {
        return topic_name;
    }
}
