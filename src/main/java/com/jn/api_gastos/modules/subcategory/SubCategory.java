package com.jn.api_gastos.modules.subcategory;

import com.jn.api_gastos.modules.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSubCategory;
    @NotBlank
    private String description;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false)
    private Category category;
    @NotNull
    private boolean state;
    @NotBlank
    @Email
    private String user;
}
