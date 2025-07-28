package com.jn.api_gastos.modules.subcategory;

import com.jn.api_gastos.modules.category.Category;

public record SubCategoryDTO(
        Integer idSubCategory,
        String description,
        Category category,
        boolean state
) {};