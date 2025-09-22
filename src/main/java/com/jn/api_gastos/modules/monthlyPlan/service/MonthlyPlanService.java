package com.jn.api_gastos.modules.monthlyPlan.service;

import com.jn.api_gastos.auth.AuthenticatedUser;
import com.jn.api_gastos.modules.monthlyPlan.dto.*;
import com.jn.api_gastos.modules.monthlyPlan.model.MonthlyPlan;
import com.jn.api_gastos.modules.monthlyPlan.model.ItemMonthlyPlan;
import com.jn.api_gastos.modules.monthlyPlan.repository.MonthlyPlanRepository;
import com.jn.api_gastos.modules.monthlyPlan.repository.ItemMonthlyPlanRepository;
import com.jn.api_gastos.modules.categoryMonthlyPlan.CategoryMonthlyPlan;
import com.jn.api_gastos.modules.categoryMonthlyPlan.CategoryMonthlyPlanRepository;
import com.jn.api_gastos.modules.category.Category;
import com.jn.api_gastos.modules.category.CategoryRepository;
import com.jn.api_gastos.modules.subcategory.SubCategory;
import com.jn.api_gastos.modules.subcategory.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MonthlyPlanService {

    private final MonthlyPlanRepository monthlyPlanRepository;
    private final ItemMonthlyPlanRepository itemMonthlyPlanRepository;
    private final CategoryMonthlyPlanRepository categoryMonthlyPlanRepository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    @Autowired
    private AuthenticatedUser authenticatedUser;

    @Transactional
    public MonthlyPlanResponseDTO createMonthlyPlan(MonthlyPlanRequestDTO requestDTO) {
        // Validar que no existe un plan para ese mes y año del usuario
        monthlyPlanRepository.findByUserAndYearAndMonth(
                requestDTO.getUser(),
                requestDTO.getYear(),
                requestDTO.getMonth()
        ).ifPresent(existingPlan -> {
            throw new IllegalArgumentException(
                    "Ya existe un plan mensual para " + requestDTO.getMonth() + "/" + requestDTO.getYear()
            );
        });

        // Obtener CategoryMonthlyPlan
        CategoryMonthlyPlan categoryMonthlyPlan = categoryMonthlyPlanRepository
                .findById(Math.toIntExact(requestDTO.getCategoryMonthlyPlanId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "CategoryMonthlyPlan no encontrada con ID: " + requestDTO.getCategoryMonthlyPlanId()
                ));

        // Crear MonthlyPlan
        MonthlyPlan monthlyPlan = MonthlyPlan.builder()
                .year(requestDTO.getYear())
                .month(requestDTO.getMonth())
                .state(requestDTO.isState())
                .categoryMonthlyPlan(categoryMonthlyPlan)
                .user(requestDTO.getUser())
                .build();

        MonthlyPlan savedMonthlyPlan = monthlyPlanRepository.save(monthlyPlan);

        // Crear Items
        List<ItemMonthlyPlan> savedItems = requestDTO.getItems().stream()
                .map(itemDTO -> createItemMonthlyPlan(itemDTO, savedMonthlyPlan))
                .collect(Collectors.toList());

        itemMonthlyPlanRepository.saveAll(savedItems);

        // Construir respuesta
        return buildResponseDTO(savedMonthlyPlan, savedItems);
    }

    private ItemMonthlyPlan createItemMonthlyPlan(ItemMonthlyPlanDTO itemDTO, MonthlyPlan monthlyPlan) {
        // Obtener Category
        Category category = categoryRepository.findById(Math.toIntExact(itemDTO.getCategoryId()))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Categoría no encontrada con ID: " + itemDTO.getCategoryId()
                ));

        // Obtener SubCategory si se proporciona
        SubCategory subCategory = null;
        if (itemDTO.getSubCategoryId() != null) {
            subCategory = subCategoryRepository.findById(Math.toIntExact(itemDTO.getSubCategoryId()))
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Subcategoría no encontrada con ID: " + itemDTO.getSubCategoryId()
                    ));
        }

        return ItemMonthlyPlan.builder()
                .description(itemDTO.getDescription())
                .estimateAmount(itemDTO.getEstimateAmount())
                .realAmount(itemDTO.getRealAmount())
                .monthlyPlan(monthlyPlan)
                .category(category)
                .subCategory(subCategory)
                .state(itemDTO.isState())
                .user(monthlyPlan.getUser())
                .build();
    }

    private MonthlyPlanResponseDTO buildResponseDTO(MonthlyPlan monthlyPlan, List<ItemMonthlyPlan> items) {
        List<ItemMonthlyPlanResponseDTO> itemsResponse = items.stream()
                .map(item -> ItemMonthlyPlanResponseDTO.builder()
                        .idItemMonthlyPlan(item.getIdItemMonthlyPlan())
                        .description(item.getDescription())
                        .estimateAmount(item.getEstimateAmount())
                        .realAmount(item.getRealAmount())
                        .categoryName(item.getCategory().getDescription())
                        .subCategoryName(item.getSubCategory() != null ? item.getSubCategory().getDescription() : null)
                        .state(item.isState())
                        .build())
                .collect(Collectors.toList());

        return MonthlyPlanResponseDTO.builder()
                .idMonthlyPlan(monthlyPlan.getIdMonthlyPlan())
                .year(monthlyPlan.getYear())
                .month(monthlyPlan.getMonth())
                .state(monthlyPlan.isState())
                .categoryMonthlyPlanId(monthlyPlan.getCategoryMonthlyPlan().getIdCategoryMonthlyPlan().longValue())
                .categoryMonthlyPlanName(monthlyPlan.getCategoryMonthlyPlan().getDescription())
                .user(monthlyPlan.getUser())
                .items(itemsResponse)
                .totalItems(itemsResponse.size())
                .build();
    }

    public MonthlyPlanResponseDTO getMonthlyPlanById(Long id) {
        MonthlyPlan monthlyPlan = monthlyPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan mensual no encontrado con ID: " + id));

        List<ItemMonthlyPlan> items = itemMonthlyPlanRepository
                .findByMonthlyPlanIdMonthlyPlan(monthlyPlan.getIdMonthlyPlan());

        return buildResponseDTO(monthlyPlan, items);
    }

    public List<MonthlyPlanResponseDTO> getMonthlyPlansByUser() {
        String username = authenticatedUser.getUsername();

        List<MonthlyPlan> monthlyPlans = monthlyPlanRepository.findByUser(username);
        System.out.println(monthlyPlans);
        return monthlyPlans.stream()
                .map(plan -> {
                    List<ItemMonthlyPlan> items = itemMonthlyPlanRepository
                            .findByMonthlyPlanIdMonthlyPlan(plan.getIdMonthlyPlan());
                    return buildResponseDTO(plan, items);
                })
                .collect(Collectors.toList());
    }

    public MonthlyPlanResponseDTO getMonthlyPlanByUserAndYearAndMonth(Integer year, Integer month) {
        String username = authenticatedUser.getUsername();

        MonthlyPlan monthlyPlan = monthlyPlanRepository.findByUserAndYearAndMonth(username, year, month)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Plan mensual no encontrado para el usuario: " + username +
                                " en " + month + "/" + year
                ));

        List<ItemMonthlyPlan> items = itemMonthlyPlanRepository
                .findByMonthlyPlanIdMonthlyPlan(monthlyPlan.getIdMonthlyPlan());

        return buildResponseDTO(monthlyPlan, items);
    }

    @Transactional
    public void deleteMonthlyPlan(Long id) {
        MonthlyPlan monthlyPlan = monthlyPlanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plan mensual no encontrado con ID: " + id));

        itemMonthlyPlanRepository.deleteByMonthlyPlanIdMonthlyPlan(id);
        monthlyPlanRepository.delete(monthlyPlan);
    }
}