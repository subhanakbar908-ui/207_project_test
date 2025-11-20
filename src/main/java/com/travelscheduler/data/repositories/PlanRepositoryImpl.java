package com.travelscheduler.data.repositories;

import com.travelscheduler.domain.common.Result;
import com.travelscheduler.domain.entities.Plan;
import com.travelscheduler.domain.repositories.PlanRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PlanRepositoryImpl implements PlanRepository {

    private final Map<String, List<Plan>> plansByUser = new ConcurrentHashMap<>();

    @Override
    public Result<List<Plan>> getPlansForUser(String userId) {
        List<Plan> plans = plansByUser.getOrDefault(userId, new ArrayList<>());
        return Result.success(new ArrayList<>(plans));
    }

    @Override
    public Result<Boolean> deletePlan(String planId, String userId) {
        List<Plan> plans = plansByUser.get(userId);
        if (plans == null) {
            return Result.error("No plans for this user.");
        }

        boolean removed = plans.removeIf(p -> p.getId().equals(planId));

        if (!removed) {
            return Result.error("Plan not found.");
        }

        return Result.success(true);
    }

    @Override
    public Result<Boolean> savePlan(Plan plan) {
        plansByUser
                .computeIfAbsent(plan.getUserId(), key -> new ArrayList<>())
                .add(plan);

        return Result.success(true);
    }
}