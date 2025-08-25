package com.jn.api_gastos.modules.category;

import com.jn.api_gastos.config.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}")
@RequiredArgsConstructor
public class CategoryController {

    private static final String NAME_ENDPOINT = "/category";
    private static final String NAME_ENDPOINT_ID = "/category/{id}";

    @Autowired
    private ICategoryService categoryService;

    @GetMapping(value = NAME_ENDPOINT)
    public ResponseEntity<List<CategoryDTO>> getCategories() {

        List<CategoryDTO> categories = categoryService.listCategories();

        if (categories == null || categories.isEmpty()) {
            throw new ResourceNotFoundException("Categories");
        }

        return ResponseEntity.ok(categories);
    }

    @PostMapping(value = NAME_ENDPOINT)
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {
        Category saved = categoryService.saveCategory(category);
        return ResponseEntity.ok(saved);
    }

    @PutMapping(value = NAME_ENDPOINT_ID)
    public ResponseEntity<Category>updateCategory(@PathVariable Integer id, @RequestBody Category getCategory) {

        Category category = categoryService.getCategoryById(id);

        if (category == null) {
            throw new ResourceNotFoundException("Categories", "idCategory", id);
        }

        category.setDescription(getCategory.getDescription());
        category.setState(getCategory.isState());
        categoryService.saveCategory(category);

        return ResponseEntity.ok(category);
    }

    @GetMapping(value = "/categorySelect")
    public ResponseEntity<List<CategoryDTO>> getCategoriesSelect() {

        List<CategoryDTO> categories = categoryService.getCategoriesActive();

        if (categories == null || categories.isEmpty()) {
            throw new ResourceNotFoundException("Categories");
        }

        return ResponseEntity.ok(categories);
    }

}
