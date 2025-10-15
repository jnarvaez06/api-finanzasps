package com.jn.api_gastos.modules.monthlyPlan.model;

import com.jn.api_gastos.modules.category.Category;
import com.jn.api_gastos.modules.categoryMonthlyPlan.CategoryMonthlyPlan;
import com.jn.api_gastos.modules.subcategory.SubCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemMonthlyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemMonthlyPlan;

    @NotBlank
    private String description;

    @DecimalMin(value = "0.01", message = "Value must be greater than 0")
    private BigDecimal estimateAmount;

    @DecimalMin(value = "0.01", message = "Value must be greater than 0")
    private BigDecimal realAmount;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_monthly_plan", referencedColumnName = "idMonthlyPlan")
    private MonthlyPlan monthlyPlan;

    @NotNull
    @OneToOne
    @JoinColumn(name = "id_category", referencedColumnName = "idCategory")
    private Category category;

    @OneToOne
    @JoinColumn(name = "id_subcategory", referencedColumnName = "idSubCategory")
    private SubCategory subCategory;

    @OneToOne
    @JoinColumn(name = "id_category_monthly_plan", referencedColumnName = "idCategoryMonthlyPlan")
    private CategoryMonthlyPlan categoryMonthlyPlan;

    @NotNull
    private boolean state;

    @NotBlank
    @Email
    private String user;
}
