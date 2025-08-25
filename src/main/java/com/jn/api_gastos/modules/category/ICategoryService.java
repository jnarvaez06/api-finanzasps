package com.jn.api_gastos.modules.category;

import java.util.List;

public interface ICategoryService {

    public List<CategoryDTO> listCategories();

    public Category saveCategory(Category category);

    public Category getCategoryById(Integer idCategory);

    public List<CategoryDTO> getCategoriesActive();
}
