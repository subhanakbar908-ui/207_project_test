package com.travelscheduler.domain.myusecases;

import com.travelscheduler.domain.common.Result;
import com.travelscheduler.domain.entities.Plan;
import com.travelscheduler.domain.repositories.PlanRepository;

import java.util.List;

public class ViewPlansUseCase {

    private final PlanRepository planRepository;

    public ViewPlansUseCase(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    public Result<List<Plan>> execute(String userId) {
        return planRepository.getPlansForUser(userId);
    }
}
