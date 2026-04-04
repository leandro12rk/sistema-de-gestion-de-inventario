package io.github.leandro12rk.product.repository.product;

import io.github.leandro12rk.product.model.product.Product;
import io.github.leandro12rk.product.projection.product.ProductGetProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<ProductGetProjection> findProjectedById(Long id);
    List<ProductGetProjection> findAllProjectedBy();
}

