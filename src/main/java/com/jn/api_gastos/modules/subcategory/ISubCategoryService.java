package com.jn.api_gastos.modules.subcategory;

import java.util.List;

public interface ISubCategoryService {

    public List<SubCategoryDTO> listSubCategories();

    public SubCategory saveSubCategory(SubCategoryRequestDTO subCategory);

    public SubCategory getSubCategoryById(Integer idSubCategory);

    public List<SubCategoryDTO> getSubCategoryActive();
}
