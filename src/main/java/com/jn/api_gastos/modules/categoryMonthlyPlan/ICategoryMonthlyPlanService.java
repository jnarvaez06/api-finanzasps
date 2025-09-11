package com.jn.api_gastos.modules.categoryMonthlyPlan;

import java.util.List;

public interface ICategoryMonthlyPlanService {
    public List<CategoryMonthlyPlanDTO> listCategoryMonthlyPlan();

    public CategoryMonthlyPlan getCategoryMonthlyPlanById(Integer idCategoryMonthlyPlan);

    public CategoryMonthlyPlan saveCategoryMonthlyPlan(CategoryMonthlyPlan categoryMonthlyPlan);

}
