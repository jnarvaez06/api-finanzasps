package com.jn.api_gastos.reports.movements;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface MovementReportProjection {
    Long getIdMovement();
    LocalDate getDateMovement();
    String getDescription();
    BigDecimal getValue();
    String getType();
    Long getAccountId();
    String getAccountDescription();
    Long getCategoryId();
    String getCategoryDescription();
    Long getSubCategoryId();
    String getSubCategoryDescription();
}