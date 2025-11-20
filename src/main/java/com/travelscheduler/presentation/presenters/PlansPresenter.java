package com.travelscheduler.presentation.presenters;

import com.travelscheduler.domain.common.Result;
import com.travelscheduler.domain.entities.Plan;
import com.travelscheduler.domain.myusecases.DeletePlanUseCase;
import com.travelscheduler.domain.myusecases.ViewPlansUseCase;
import com.travelscheduler.presentation.views.PlansView;

import java.util.List;

public class PlansPresenter {

    private PlansView view;
    private final ViewPlansUseCase viewPlansUseCase;
    private final DeletePlanUseCase deletePlanUseCase;
    private final String userId;   // who we are showing plans for

    public PlansPresenter(PlansView view,
                          ViewPlansUseCase viewPlansUseCase,
                          DeletePlanUseCase deletePlanUseCase,
                          String userId) {
        this.view = view;
        this.viewPlansUseCase = viewPlansUseCase;
        this.deletePlanUseCase = deletePlanUseCase;
        this.userId = userId;
    }

    public void setView(PlansView view) {
        this.view = view;
    }

    public void loadPlans() {
        Result<List<Plan>> result = viewPlansUseCase.execute(userId);
        if (result.isSuccess()) {
            view.showPlans(result.getData());
        } else {
            view.showError(result.getError());
        }
    }

    public void deletePlan(String planId) {
        Result<Boolean> result = deletePlanUseCase.execute(planId, userId);
        if (result.isSuccess() && Boolean.TRUE.equals(result.getData())) {
            view.showInfo("Plan deleted.");
            loadPlans();
        } else {
            view.showError(result.getError());
        }
    }
}