package com.jn.api_gastos.modules.movement;

import com.jn.api_gastos.modules.account.Account;
import com.jn.api_gastos.modules.category.Category;
import com.jn.api_gastos.modules.subcategory.SubCategory;

import java.math.BigDecimal;

public record MovementDTO(
        Long idMovement,
        String description,
        String type,
        @jakarta.validation.constraints.NotNull
        @jakarta.validation.constraints.DecimalMin(value = "0.01", message = "Value must be greater than 0")
        BigDecimal value,
        @jakarta.validation.constraints.NotNull
        java.time.LocalDate dateMovement,
        Account account,
        Category category,
        SubCategory subCategory
) {};
