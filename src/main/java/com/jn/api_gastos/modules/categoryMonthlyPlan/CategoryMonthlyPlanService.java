package com.jn.api_gastos.modules.categoryMonthlyPlan;

import com.jn.api_gastos.auth.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMonthlyPlanService implements ICategoryMonthlyPlanService{

    @Autowired
    private CategoryMonthlyPlanRepository repository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Override
    public List<CategoryMonthlyPlanDTO> listCategoryMonthlyPlan() {
        String username = authenticatedUser.getUsername();

        return repository.findAllByUser(username)
                .stream()
                .map(category -> new CategoryMonthlyPlanDTO(
                        category.getIdCategoryMonthlyPlan(),
                        category.getDescription(),
                        category.getType(),
                        category.isState()
                ))
                .toList();
    }

    @Override
    public CategoryMonthlyPlan getCategoryMonthlyPlanById(Integer idCategoryMonthlyPlan) {
        return null;
    }

    @Override
    public CategoryMonthlyPlan saveCategoryMonthlyPlan(CategoryMonthlyPlan categoryMonthlyPlan) {
        return repository.save(categoryMonthlyPlan);
    }
}
