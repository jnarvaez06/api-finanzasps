package com.jn.api_gastos.modules.movement;

import com.jn.api_gastos.config.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("${api.base.path}")
@RequiredArgsConstructor
public class MovementController {
    private static final String NAME_ENDPOINT = "/movement";
    private static final String NAME_ENDPOINT_ID = "/movement/{id}";

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

}
