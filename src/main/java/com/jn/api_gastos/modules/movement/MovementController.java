package com.jn.api_gastos.modules.movement;

import com.jn.api_gastos.config.exception.ResourceNotFoundException;
import com.jn.api_gastos.modules.account.Account;
import com.jn.api_gastos.modules.account.IAccountService;
import com.jn.api_gastos.modules.category.Category;
import com.jn.api_gastos.modules.category.CategoryService;
import com.jn.api_gastos.modules.category.ICategoryService;
import com.jn.api_gastos.modules.subcategory.ISubCategoryService;
import com.jn.api_gastos.modules.subcategory.SubCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("${api.base.path}")
@RequiredArgsConstructor
public class MovementController {

    private static final String NAME_ENDPOINT = "/movement";
    private static final String NAME_ENDPOINT_ID = "/movement/{idMovement}";

    @Autowired
    private IMovementService movementService;

    @GetMapping(value = NAME_ENDPOINT)
    public ResponseEntity<List<MovementDTO>> getMovements () {
        List<MovementDTO> movements = movementService.listMovements();

        if (movements == null || movements.isEmpty()) {
            throw new ResourceNotFoundException("Movements");
        }

        return ResponseEntity.ok(movements);
    }

    @PostMapping(value = NAME_ENDPOINT)
    public ResponseEntity<Movement> addMovement(@Valid @RequestBody MovementRequestDTO requestDTO) {
        Movement saved = movementService.saveMovement(requestDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping(value = NAME_ENDPOINT_ID)
    public ResponseEntity<Movement> updateMovement(@PathVariable Integer idMovement, @Valid @RequestBody MovementRequestDTO requestDTO) {

        requestDTO.setIdMovement(idMovement.longValue());
        Movement updated = movementService.saveMovement(requestDTO);

        return ResponseEntity.ok(updated);
    }
}
