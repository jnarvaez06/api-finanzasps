package com.jn.api_gastos.modules.categoryMonthlyPlan;

public record CategoryMonthlyPlanDTO (
        Integer idCategoryMonthlyPlan,
        String description,
        String type,
        boolean state
) {}
