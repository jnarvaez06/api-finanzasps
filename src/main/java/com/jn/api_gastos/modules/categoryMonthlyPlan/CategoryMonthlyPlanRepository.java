package com.jn.api_gastos.modules.categoryMonthlyPlan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryMonthlyPlanRepository extends JpaRepository<CategoryMonthlyPlan, Integer> {
    List<CategoryMonthlyPlan> findAllByUser(String user);
}
