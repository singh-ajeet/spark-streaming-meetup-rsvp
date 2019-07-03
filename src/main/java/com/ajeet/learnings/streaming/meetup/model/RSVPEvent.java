package com.ajeet.learnings.streaming.meetup.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

public final class RSVPEvent implements Serializable {
    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final String rsvp_id;
    private final Venue venue;
    private final String visibility;
    private final String response;
    private final int guests;
    private final long mtime;
    private final Group group;
    private final Member member;
    private final Event event;

    public RSVPEvent(String rsvp_id, Venue venue, String visibility, String response,
                     int guests, long mtime, Group group, Member member, Event event) {
        this.rsvp_id = rsvp_id;
        this.venue = venue;
        this.visibility = visibility;
        this.response = response;
        this.guests = guests;
        this.mtime = mtime;
        this.group = group;
        this.member = member;
        this.event = event;
    }

    @JsonCreator
    public static RSVPEvent createRSVPEvent(
            @JsonProperty("rsvp_id") String rsvp_id,
            @JsonProperty("venue") Venue venue,
            @JsonProperty("visibility") String visibility,
            @JsonProperty("response") String response,
            @JsonProperty("guests") int guests,
            @JsonProperty("mtime") long mtime,
            @JsonProperty("group") Group group,
            @JsonProperty("member") Member member,
            @JsonProperty("event") Event event){

        return new RSVPEvent(rsvp_id, venue, visibility, response, guests, mtime, group, member, event);
    }

    public String getRsvp_id() {
        return rsvp_id;
    }

    public Venue getVenue() {
        return venue;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getResponse() {
        return response;
    }

    public int getGuests() {
        return guests;
    }

    public long getMtime() {
        return mtime;
    }

    public Group getGroup() {
        return group;
    }

    public Member getMember() {
        return member;
    }

    public Event getEvent() {
        return event;
    }

    @JsonIgnore
    public static RSVPEvent parse(String json){
        try {
            return mapper.readValue(json, RSVPEvent.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

 /*   public static void main(String[] args) throws IOException {

        RSVPEvent event = parse("{\"venue\":{\"venue_name\":\"Microsoft Reactor\",\"lon\":-122.39823,\"lat\":37.784622,\"venue_id\":26043648},\"visibility\":\"public\",\"response\":\"yes\",\"guests\":0,\"member\":{\"member_id\":200173526,\"photo\":\"https://secure.meetupstatic.com/photos/member/c/2/6/0/thumb_254089760.jpeg\",\"member_name\":\"Karen\"},\"rsvp_id\":1794771557,\"mtime\":1562136510051,\"event\":{\"event_name\":\"Kubernetes in Production / Tekton CI/CD\",\"event_id\":\"262831812\",\"time\":1565312400000,\"event_url\":\"https://www.meetup.com/sf-DevOps/events/262831812/\"},\"group\":{\"group_topics\":[{\"urlkey\":\"high-scalability-computing\",\"topic_name\":\"High Scalability Computing\"},{\"urlkey\":\"iaas-infrastructure-as-a-service\",\"topic_name\":\"IaaS (Infrastructure as a Service)\"},{\"urlkey\":\"sysadmin\",\"topic_name\":\"System Administration\"},{\"urlkey\":\"paas\",\"topic_name\":\"PaaS (Platform as a Service)\"},{\"urlkey\":\"cloud-computing\",\"topic_name\":\"Cloud Computing\"},{\"urlkey\":\"linux\",\"topic_name\":\"Linux\"},{\"urlkey\":\"data-center-networking-and-design\",\"topic_name\":\"Data Center Networking and Design\"},{\"urlkey\":\"technology\",\"topic_name\":\"Technology\"},{\"urlkey\":\"mysql\",\"topic_name\":\"MySQL\"},{\"urlkey\":\"devops\",\"topic_name\":\"DevOps\"},{\"urlkey\":\"amazon-web-services\",\"topic_name\":\"Amazon Web Services\"},{\"urlkey\":\"docker\",\"topic_name\":\"Docker\"},{\"urlkey\":\"opscode-chef\",\"topic_name\":\"Opscode Chef\"},{\"urlkey\":\"apis\",\"topic_name\":\"APIs\"},{\"urlkey\":\"opensource\",\"topic_name\":\"Open Source\"}],\"group_city\":\"San Francisco\",\"group_country\":\"us\",\"group_id\":1109628,\"group_name\":\"SF DevOps\",\"group_lon\":-122.4,\"group_urlname\":\"sf-DevOps\",\"group_state\":\"CA\",\"group_lat\":37.8}}");
        System.out.println(event);
    }
*/}
