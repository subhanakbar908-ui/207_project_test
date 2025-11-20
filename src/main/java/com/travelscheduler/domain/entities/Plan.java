package com.travelscheduler.domain.entities;

import java.util.List;

public class Plan {

    private final String id;
    private final String userId;

    private final String city;
    private final float radius;
    private final String[] interests;
    private final String[] locations;

    private final List<PlanVisit> visits;  // ordered result of plan

    public Plan(String id,
                String userId,
                String city,
                float radius,
                String[] interests,
                String[] locations,
                List<PlanVisit> visits) {

        this.id = id;
        this.userId = userId;
        this.city = city;
        this.radius = radius;
        this.interests = interests;
        this.locations = locations;
        this.visits = visits;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getCity() {
        return city;
    }

    public float getRadius() {
        return radius;
    }

    public String[] getInterests() {
        return interests;
    }

    public String[] getLocations() {
        return locations;
    }

    public List<PlanVisit> getVisits() {
        return visits;
    }
}