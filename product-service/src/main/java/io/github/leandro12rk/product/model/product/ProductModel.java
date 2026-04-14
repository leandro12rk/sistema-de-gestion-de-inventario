package io.github.leandro12rk.product.model.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.leandro12rk.product.model.supplier.SupplierModel;
import io.github.leandro12rk.product.model.category.CategoryModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor // Esto reemplaza al constructor vacío manual "public Product() {}"
@AllArgsConstructor
@Entity
@Table(name = "products") // Nombre de la tabla en Postgres
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Missing Product SKU ")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "El SKU solo permite letras, números y guiones")
    private String sku;

    @Column(nullable = false, length = 100)
    @NotBlank(message = "Missing Product Name")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String name;

    @NotBlank(message = "Missing Product Description")
    private String description;

    @Positive(message = "Missing Product Price")
    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id") // Apunta al id de categories
    @JsonProperty("category_id")
    @NotNull(message = "Missing Product Category")
    private CategoryModel category;

    @NotNull(message = "Missing Product Supplier")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id") // Apunta al id de suppliers
    private SupplierModel supplier;

    @NotNull(message = "Missing product Status")
    private boolean status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = " updated_at", insertable = false, updatable = false)
    private java.time.LocalDateTime  updatedAt;

}