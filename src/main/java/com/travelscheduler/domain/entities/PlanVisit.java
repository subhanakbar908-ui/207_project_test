package com.travelscheduler.domain.entities;

public class PlanVisit {

    private final String placeName;
    private final String address;
    private final int timeToReach;   // minutes from previous location
    private final int duration;      // minutes spent at place

    public PlanVisit(String placeName, String address, int timeToReach, int duration) {
        this.placeName = placeName;
        this.address = address;
        this.timeToReach = timeToReach;
        this.duration = duration;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getAddress() {
        return address;
    }

    public int getTimeToReach() {
        return timeToReach;
    }

    public int getDuration() {
        return duration;
    }
}