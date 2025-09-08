package com.jn.api_gastos.reports.movements;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovementReportService {

    private final MovementReportRepository repository;

    public MovementReportService(MovementReportRepository repository) {
        this.repository = repository;
    }

    public List<MovementReportProjection> getReport(MovementFilterDTO filterDTO) {
        return repository.findMovementsReport(
            filterDTO.getYear(),
            filterDTO.getMonth(),
            filterDTO.getAccountId(),
            filterDTO.getCategoryId(),
            filterDTO.getSubCategoryId()
        );
    }


}
