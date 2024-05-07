package com.niamedtech.expo.exposerversdk.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.*;

public final class PushNotification {

    public enum Priority {
        @JsonProperty("default")
        OK, @JsonProperty("high")
        ERROR, @JsonProperty("normal")
        NORMAL;
    }

    public static final class Sound {
        private Boolean critical;
        private String name;
        private Long volume;

        public Sound(Sound other) {
            this.critical = other.critical;
            this.name = other.name;
            this.volume = other.volume;
        }

        public Boolean getCritical() {
            return critical;
        }

        public void setCritical(Boolean critical) {
            this.critical = critical;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getVolume() {
            return volume;
        }

        public void setVolume(Long volume) {
            this.volume = volume;
        }

    }

    private List<String> to;

    private Map<String, Object> data;

    private String title;

    private String subtitle;

    private String body;

    private Sound sound;

    private Long ttl;

    private Long expiration;

    private Priority priority;

    private Long badge;

    private String channelId;

    public PushNotification() {
        super();
        // TODO Auto-generated constructor stub
    }

    public PushNotification(PushNotification other) {
        this.to = other.to;
        this.title = other.title;
        this.subtitle = other.subtitle;
        this.body = other.body;
        if (other.sound != null) {
            this.sound = new Sound(other.sound);
        }
        this.ttl = other.ttl;
        this.expiration = other.expiration;
        this.priority = other.priority;
        this.badge = other.badge;
        this.channelId = other.channelId;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getBadge() {
        return badge;
    }

    public void setBadge(Long badge) {
        this.badge = badge;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

}
