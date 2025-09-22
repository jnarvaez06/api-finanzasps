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
    private String categoryName;
    private String subCategoryName;
    private boolean state;
}