package com.jn.api_gastos.modules.category;

import com.jn.api_gastos.config.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}")
@RequiredArgsConstructor
public class CategoryController {

    private static final String NAME_ENDPOINT = "/category";

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
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category){
        Category saved = categoryService.saveCategory(category);
        return ResponseEntity.ok(saved);
    }
}
