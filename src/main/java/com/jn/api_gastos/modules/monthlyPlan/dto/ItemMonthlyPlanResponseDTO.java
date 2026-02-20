package com.jn.api_gastos.modules.monthlyPlan.dto;

import lombok.Data;
import lombok.Builder;
import java.math.BigDecimal;

@Data
@Builder
public class ItemMonthlyPlanResponseDTO {
    private Long idItemMonthlyPlan;
    private String description;
    private BigDecimal estimateAmount;
    private BigDecimal realAmount;
    private Long categoryMonthlyPlanId;
    private String categoryMonthlyPlanName;
    private Long categoryId;
    private String categoryName;
    private Long subCategoryId;
    private String subCategoryName;
    private boolean state;
    private boolean isRecurrent;
}