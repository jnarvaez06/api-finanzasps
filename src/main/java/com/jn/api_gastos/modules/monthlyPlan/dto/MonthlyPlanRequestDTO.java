package com.jn.api_gastos.modules.monthlyPlan.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class MonthlyPlanRequestDTO {
    @NotNull
    private Integer year;

    @NotNull
    private Integer month;

    @NotNull
    private boolean state;

    @NotNull
    private Long categoryMonthlyPlanId;

    @NotBlank
    @Email
    private String user;

    @Valid
    @NotNull
    private List<ItemMonthlyPlanDTO> items;
}
