package io.github.leandro12rk.product.repository.supplier;

import io.github.leandro12rk.product.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Category, Long> {
}
