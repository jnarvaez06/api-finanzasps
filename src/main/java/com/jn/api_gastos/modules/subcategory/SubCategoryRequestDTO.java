package com.jn.api_gastos.modules.subcategory;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SubCategoryRequestDTO {
    @NotBlank
    private String description;

    @NotNull
    private Integer idCategory;

    @NotNull
    private boolean state;

    @NotBlank
    @Email
    private String user;
}
