package com.jn.api_gastos.modules.monthlyPlan.controller;

import com.jn.api_gastos.config.exception.ResourceNotFoundException;
import com.jn.api_gastos.modules.monthlyPlan.dto.MonthlyPlanRequestDTO;
import com.jn.api_gastos.modules.monthlyPlan.dto.MonthlyPlanResponseDTO;
import com.jn.api_gastos.modules.monthlyPlan.service.MonthlyPlanService;
import com.jn.api_gastos.modules.subcategory.SubCategoryDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.base.path}")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MonthlyPlanController {
    private static final String NAME_ENDPOINT = "/monthlyPlan";
    private static final String NAME_ENDPOINT_ID = "/monthlyPlan/{id}";
    private static final String NAME_ENDPOINT_DATE = "/monthlyPlan/{year}/{month}";

    private final MonthlyPlanService monthlyPlanService;

    @PostMapping(value = NAME_ENDPOINT)
    public ResponseEntity<MonthlyPlanResponseDTO> createMonthlyPlan(
            @Valid @RequestBody MonthlyPlanRequestDTO requestDTO) {
        try {
            MonthlyPlanResponseDTO response = monthlyPlanService.createMonthlyPlan(requestDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = NAME_ENDPOINT)
    public ResponseEntity<List<MonthlyPlanResponseDTO>> getMonthlyPlansByUser() {
        try {
            List<MonthlyPlanResponseDTO> response = monthlyPlanService.getMonthlyPlansByUser();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = NAME_ENDPOINT_ID)
    public ResponseEntity<MonthlyPlanResponseDTO> getMonthlyPlanById(@PathVariable Long id) {
        try {
            MonthlyPlanResponseDTO response = monthlyPlanService.getMonthlyPlanById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = NAME_ENDPOINT_DATE)
    public ResponseEntity<MonthlyPlanResponseDTO> getMonthlyPlanByUserAndYearAndMonth(
            @PathVariable Integer year,
            @PathVariable Integer month) {
        try {
            MonthlyPlanResponseDTO response = monthlyPlanService.getMonthlyPlanByUserAndYearAndMonth(year, month);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = NAME_ENDPOINT_ID)
    public ResponseEntity<Void> deleteMonthlyPlan(@PathVariable Long id) {
        try {
            monthlyPlanService.deleteMonthlyPlan(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}