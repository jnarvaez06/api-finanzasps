package com.jn.api_gastos.modules.subcategory;

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
public class SubCategoryController {

    private static final String NAME_ENDPOINT = "/subcategory";
    private static final String NAME_ENDPOINT_ID = "/subcategory/{id}";

    @Autowired
    private ISubCategoryService subCategoryService;

    @GetMapping(value = NAME_ENDPOINT)
    public ResponseEntity<List<SubCategoryDTO>> getSubCategories() {
        List<SubCategoryDTO> subcategories = subCategoryService.listSubCategories();

        if (subcategories == null || subcategories.isEmpty()) {
            throw new ResourceNotFoundException("SubCategories");
        }

        return ResponseEntity.ok(subcategories);
    }

    @PostMapping(value = NAME_ENDPOINT)
    public ResponseEntity<SubCategory> addSubCategory(@Valid @RequestBody SubCategoryRequestDTO requestDTO) {
        SubCategory saved = subCategoryService.saveSubCategory(requestDTO);
        return ResponseEntity.ok(saved);
    }
}
