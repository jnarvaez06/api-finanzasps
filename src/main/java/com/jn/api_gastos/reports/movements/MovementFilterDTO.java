package com.jn.api_gastos.reports.movements;

import lombok.Data;

@Data
public class MovementFilterDTO {
    private Integer year;
    private Integer month;
    private Long accountId;
    private Long categoryId;
    private Long subCategoryId;
}
