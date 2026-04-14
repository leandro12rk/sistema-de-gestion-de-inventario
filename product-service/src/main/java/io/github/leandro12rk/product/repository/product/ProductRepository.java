package io.github.leandro12rk.product.repository.product;

import io.github.leandro12rk.product.model.product.ProductModel;
import io.github.leandro12rk.product.projection.product.ProductGetProjection;
import io.github.leandro12rk.product.projection.product.ProductNameProjection;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> , JpaSpecificationExecutor<ProductModel> {
    Optional<ProductGetProjection> findProjectedById(Long id);
    Optional<ProductNameProjection> findNameProjectById(Long id);
}

