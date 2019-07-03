package com.ajeet.learnings.streaming.meetup.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.net.URI;

public final class Member implements Serializable {
    private final String member_id;
    private final String member_name;
    private final URI photo;

    public Member(String id, String name, URI photo) {
        this.member_id = id;
        this.member_name = name;
        this.photo = photo;
    }

    @JsonCreator
    public static Member createMember(
            @JsonProperty("member_id") String member_id,
            @JsonProperty("member_name") String member_name,
            @JsonProperty("photo") URI photo){

        return new Member(member_id, member_name, photo);
    }

    public String getMember_id() {
        return member_id;
    }

    public String getName() {
        return member_name;
    }

    public URI getPhoto() {
        return photo;
    }
}
