package com.jn.api_gastos.reports.movements;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("${api.base.path}")
public class MovementReportController {

    private static final String NAME_ENDPOINT = "/reports/movement";
    private final MovementReportService service;

    public MovementReportController(MovementReportService service) {
        this.service = service;
    }

    @PostMapping(value = NAME_ENDPOINT)
    public List<MovementReportProjection> getMovements(@RequestBody MovementFilterDTO filter) {
        return service.getReport(filter);
    }
}
