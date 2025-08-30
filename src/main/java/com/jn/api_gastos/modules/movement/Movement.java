package com.jn.api_gastos.modules.movement;

import com.jn.api_gastos.modules.account.Account;
import com.jn.api_gastos.modules.category.Category;
import com.jn.api_gastos.modules.subcategory.SubCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovement;

    @NotBlank
    private String description;

    @Column(nullable = false, length = 1, columnDefinition = "CHAR(1)")
    @Pattern(regexp = "[IG]", message = "Type must be 'I' or 'G'")
    private String type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_account", referencedColumnName = "idAccount")
    private Account account;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateMovement;

    @Column(nullable = false)
    @DecimalMin(value = "0.01", message = "Value must be greater than 0")
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "id_category", referencedColumnName = "idCategory")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "id_subcategory", referencedColumnName = "idSubCategory")
    private SubCategory subCategory;

    @Column(nullable = false)
    private LocalDate dateCreated;

    @Column(nullable = false)
    private LocalTime hourCreated;

    @NotBlank
    @Email
    private String user;

    private String transferReference;

    @PrePersist
    public void prePersist() {
        if (this.dateCreated == null) {
            this.dateCreated = LocalDate.now();
        }
        if (this.hourCreated == null) {
            this.hourCreated = LocalTime.now();
        }
    }
}
