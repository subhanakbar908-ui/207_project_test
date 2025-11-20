package com.travelscheduler.domain.repositories;

import com.travelscheduler.domain.common.Result;
import com.travelscheduler.domain.entities.Plan;

import java.util.List;

public interface PlanRepository {

    Result<List<Plan>> getPlansForUser(String userId);

    Result<Boolean> deletePlan(String planId, String userId);

    Result<Boolean> savePlan(Plan plan);
}