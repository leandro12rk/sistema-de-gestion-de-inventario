package io.github.leandro12rk.product.projection.product;

import org.springframework.beans.factory.annotation.Value;

public interface ProductGetProjection {

    Long getId();
    String getSku();
    String getName();
    Double getPrice();
    Boolean getStatus();
    String getDescription();
    java.time.LocalDateTime getCreatedAt();
    java.time.LocalDateTime getUpdatedAt();

    // Traemos el nombre de la categoría
    @Value("#{target.category.name}")
    String getCategoryName();
    // Traemos el ID de la categoría
    @Value("#{target.category.id}")
    Long getCategoryId();
    // Traemos el nombre de la empresa proveedora
    @Value("#{target.supplier.companyName}")
    String getSupplierName();
    // Traemos el ID de la empresa proveedora
    @Value("#{target.supplier.id}")
    Long getSupplierId();
}