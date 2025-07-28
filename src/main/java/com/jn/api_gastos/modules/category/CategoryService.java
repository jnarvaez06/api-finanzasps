package com.jn.api_gastos.modules.category;

import com.jn.api_gastos.auth.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Override
    public List<CategoryDTO> listCategories() {
        String username = authenticatedUser.getUsername();

        return categoryRepository.findAllByUser(username).stream().
                map(category -> new CategoryDTO(
                        category.getIdCategory(),
                        category.getDescription(),
                        category.isState()
                ))
                .toList();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Integer idCategory) {
        return categoryRepository.findById(idCategory).orElse(null);
    }
}
