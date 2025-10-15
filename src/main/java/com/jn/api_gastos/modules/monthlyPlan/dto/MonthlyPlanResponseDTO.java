package com.jn.api_gastos.modules.monthlyPlan.dto;

import lombok.Data;
import lombok.Builder;
import java.util.List;

@Data
@Builder
public class MonthlyPlanResponseDTO {
    private Long idMonthlyPlan;
    private Integer year;
    private Integer month;
    private boolean state;
    private String user;
    private List<ItemMonthlyPlanResponseDTO> items;
    private Integer totalItems;
}