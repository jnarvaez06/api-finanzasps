package com.jn.api_gastos.reports.movements;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MovementReportDTO {
    private Long idMovement;
    private LocalDate dateMovement;
    private String description;
    private BigDecimal value;
    private String type;
    private Long accountId;
    private String accountDescription;
    private Long categoryId;
    private String categoryDescription;
    private Long subCategoryId;
    private String subCategoryDescription;
}
