package com.jn.api_gastos.modules.monthlyPlan.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemMonthlyPlanDTO {
    @NotBlank
    private String description;

    @DecimalMin(value = "0.01", message = "Value must be greater than 0")
    private BigDecimal estimateAmount;

    @DecimalMin(value = "0.01", message = "Value must be greater than 0")
    private BigDecimal realAmount;

    @NotNull
    private Long categoryId;

    private Long subCategoryId; // Opcional

    @NotNull
    private boolean state;
}