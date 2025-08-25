package com.jn.api_gastos.modules.movement;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MovementRequestDTO {
    @NotBlank
    private String description;

    @Pattern(regexp = "[IG]", message = "Type must be 'I' or 'G'")
    private String type;

    @NotNull
    private Integer IdAccount;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateMovement;

    @DecimalMin(value = "0,01", message = "Value must be greater than 0")
    private float value;

    @NotNull
    private Integer idCategory;

    private Integer idSubCategory;

    @NotBlank
    @Email
    private String user;

    private String transferReference;
}
