package io.github.leandro12rk.product.repository.supplier;


import io.github.leandro12rk.product.model.supplier.SupplierModel;
import io.github.leandro12rk.product.projection.product.ProductNameProjection;
import io.github.leandro12rk.product.projection.supplier.SupplierNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierModel, Long> {
    Optional<SupplierNameProjection> findCompanyNameProjectionById(Long id);
}
