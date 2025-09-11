package com.jn.api_gastos.modules.categoryMonthlyPlan;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryMonthlyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoryMonthlyPlan;
    @NotBlank
    private String description;
    @Column(nullable = false, length = 1, columnDefinition = "CHAR(1)")
    @Pattern(regexp = "[IG]", message = "Type must be 'I' or 'G'")
    private String type;
    @NotNull
    private boolean state;
    @NotBlank
    @Email
    private String user;
}
