package com.travelscheduler.presentation.views;

import com.travelscheduler.domain.entities.Plan;

import java.util.List;

public interface PlansView {
    void showPlans(List<Plan> plans);

    void showError(String message);

    void showInfo(String message);
}