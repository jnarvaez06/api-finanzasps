package com.jn.api_gastos.modules.category;

import com.jn.api_gastos.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> listCategories() {
        return categoryRepository.findAll().stream().
                map(category -> new CategoryDTO(
                        category.getIdCategory(),
                        category.getDescription()
                ))
                .toList();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
