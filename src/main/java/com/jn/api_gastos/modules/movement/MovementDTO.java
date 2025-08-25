package com.jn.api_gastos.modules.movement;

import com.jn.api_gastos.modules.account.Account;
import com.jn.api_gastos.modules.category.Category;
import com.jn.api_gastos.modules.subcategory.SubCategory;

public record MovementDTO(
        Long idMovement,
        String description,
        String type,
        Float value,
        Account account,
        Category category,
        SubCategory subCategory
) {};
