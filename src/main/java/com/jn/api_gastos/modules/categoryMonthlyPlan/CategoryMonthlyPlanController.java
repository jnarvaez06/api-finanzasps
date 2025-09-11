package com.jn.api_gastos.modules.categoryMonthlyPlan;

import com.jn.api_gastos.config.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("${api.base.path}")
@RequiredArgsConstructor
public class CategoryMonthlyPlanController {
    private static final String NAME_ENDPOINT = "/categoryMonthlyPlan";
    private static final String NAME_ENDPOINT_ID = "/categoryMonthlyPlan/{id}";

    @Autowired
    ICategoryMonthlyPlanService planService;

    @GetMapping(value = NAME_ENDPOINT)
    public ResponseEntity<List<CategoryMonthlyPlanDTO>> getCategoryMonthlyPlan () {
        List<CategoryMonthlyPlanDTO> categoryMonthlyPlanDTOS = planService.listCategoryMonthlyPlan();

        if (categoryMonthlyPlanDTOS == null || categoryMonthlyPlanDTOS.isEmpty()) {
            throw new ResourceNotFoundException("CategoryMonthlyPlan");
        }

        return ResponseEntity.ok(categoryMonthlyPlanDTOS);
    }

    @PostMapping(value = NAME_ENDPOINT)
    public ResponseEntity<CategoryMonthlyPlan> addCategoryMonthlyPlan(@Valid @RequestBody CategoryMonthlyPlan categoryMonthlyPlan) {
        CategoryMonthlyPlan saved = planService.saveCategoryMonthlyPlan(categoryMonthlyPlan);
        return ResponseEntity.ok(saved);
    }
}
