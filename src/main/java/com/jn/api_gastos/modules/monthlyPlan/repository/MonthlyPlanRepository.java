package com.jn.api_gastos.modules.monthlyPlan.repository;

import com.jn.api_gastos.modules.monthlyPlan.model.MonthlyPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MonthlyPlanRepository extends JpaRepository<MonthlyPlan, Long> {
    List<MonthlyPlan> findByUserAndState(String user, boolean state);
    Optional<MonthlyPlan> findByUserAndYearAndMonth(String user, Integer year, Integer month);
    List<MonthlyPlan> findByUser(String user);
}