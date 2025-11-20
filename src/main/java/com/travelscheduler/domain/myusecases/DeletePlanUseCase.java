package com.travelscheduler.domain.myusecases;

import com.travelscheduler.domain.common.Result;
import com.travelscheduler.domain.repositories.PlanRepository;

public class DeletePlanUseCase {

    private final PlanRepository planRepository;

    public DeletePlanUseCase(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Result<Boolean> execute(String planId, String userId) {
        return planRepository.deletePlan(planId, userId);
    }
}