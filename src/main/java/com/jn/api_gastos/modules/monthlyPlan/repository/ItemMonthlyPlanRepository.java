package com.jn.api_gastos.modules.monthlyPlan.repository;

import com.jn.api_gastos.modules.monthlyPlan.model.ItemMonthlyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemMonthlyPlanRepository extends JpaRepository<ItemMonthlyPlan, Long> {
    List<ItemMonthlyPlan> findByMonthlyPlanIdMonthlyPlan(Long monthlyPlanId);
    List<ItemMonthlyPlan> findByMonthlyPlanIdMonthlyPlanAndState(Long monthlyPlanId, boolean state);
    void deleteByMonthlyPlanIdMonthlyPlan(Long monthlyPlanId);
}