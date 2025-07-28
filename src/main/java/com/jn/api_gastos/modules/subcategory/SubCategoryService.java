package com.jn.api_gastos.modules.subcategory;

import com.jn.api_gastos.auth.AuthenticatedUser;
import com.jn.api_gastos.config.exception.CustomMessageException;
import com.jn.api_gastos.config.exception.ResourceNotFoundException;
import com.jn.api_gastos.modules.category.Category;
import com.jn.api_gastos.modules.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryService implements ISubCategoryService{

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Override
    public List<SubCategoryDTO> listSubCategories() {
        String username = authenticatedUser.getUsername();

        return subCategoryRepository.findAllByUser(username)
                .stream()
                .map(subCategory ->new SubCategoryDTO(
                        subCategory.getIdSubCategory(),
                        subCategory.getDescription(),
                        subCategory.getCategory(),
                        subCategory.isState()
                ))
                .toList();
    }

    @Override
    public SubCategory saveSubCategory(SubCategoryRequestDTO subCategoryRequest) {

        Category category = categoryRepository.findById(subCategoryRequest.getIdCategory())
                .orElse(null);

        if (category == null) {
            throw new ResourceNotFoundException("Category", "idCategory", subCategoryRequest.getIdCategory());
        }

        SubCategory subCategory = SubCategory.builder()
                .description(subCategoryRequest.getDescription())
                .category(category)
                .state(subCategoryRequest.isState())
                .user(subCategoryRequest.getUser())
                .build();

        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory getSubCategoryById(Integer idSubCategory) {
        return subCategoryRepository.findById(idSubCategory).orElse(null);
    }
}
