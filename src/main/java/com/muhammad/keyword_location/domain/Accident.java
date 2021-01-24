package com.muhammad.keyword_location.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Accident {
    @Id
    private long tweetId;
    private String tweetUser;
    private String tweetURL;
    private String tweetText;
    private double tweetLat;
    private double tweetLng;
    private String tweetUserLocation;

    public Accident() {}

    public Accident(long tweetId, String tweetUser, String tweetText, double tweetLat, double tweetLng, String tweetUserLocation) {
        this.tweetId = tweetId;
        this.tweetUser = tweetUser;
        this.tweetText = tweetText;
        this.tweetLat = tweetLat;
        this.tweetLng = tweetLng;
        this.tweetUserLocation = tweetUserLocation;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public String getTweetUser() {
        return tweetUser;
    }

    public void setTweetUser(String tweetUser) {
        this.tweetUser = tweetUser;
    }

    public String getTweetURL() {
        return tweetURL;
    }

    public void setTweetURL(String tweetURL) {
        this.tweetURL = tweetURL;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public double getTweetLat() {
        return tweetLat;
    }

    public void setTweetLat(double tweetLat) {
        this.tweetLat = tweetLat;
    }

    public double getTweetLng() {
        return tweetLng;
    }

    public void setTweetLng(double tweetLng) {
        this.tweetLng = tweetLng;
    }

    public String getTweetUserLocation() {
        return tweetUserLocation;
    }

    public void setTweetUserLocation(String tweetLocation) {
        this.tweetUserLocation = tweetLocation;
    }

    @Override
    public String toString() {
        return "Accident{" +
                "tweetId=" + tweetId +
                ", tweetUser='" + tweetUser + '\'' +
                ", tweetText='" + tweetText + '\'' +
                ", tweetLat=" + tweetLat +
                ", tweetLng=" + tweetLng +
                ", tweetLocation='" + tweetUserLocation + '\'' +
                '}';
    }
}