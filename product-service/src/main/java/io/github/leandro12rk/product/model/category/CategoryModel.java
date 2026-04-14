package io.github.leandro12rk.product.model.category;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories") // Nombre de la tabla en Postgres
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Missing Category Name")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @NotBlank(message = "Missing Category Description")
    private String description;
    
    @NotNull(message ="Missing Category Status")
    private boolean status;
}
