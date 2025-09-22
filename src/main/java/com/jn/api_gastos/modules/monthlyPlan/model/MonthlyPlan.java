package com.jn.api_gastos.modules.monthlyPlan.model;

import com.jn.api_gastos.modules.categoryMonthlyPlan.CategoryMonthlyPlan;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonthlyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMonthlyPlan;
    @NotNull
    private Integer year;
    @NotNull
    private Integer month;
    @NotNull
    private boolean state;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_category_monthly_plan", nullable = false)
    private CategoryMonthlyPlan categoryMonthlyPlan;
    @NotBlank
    @Email
    private String user;
}
