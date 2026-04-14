package io.github.leandro12rk.purchase.client.supplier;

import io.github.leandro12rk.purchase.dto.supplier.SupplierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", contextId = "supplierClient")
public interface SupplierClient {
    @GetMapping("/supplier/{supplierId}/name")
    SupplierDTO getSupplierCompanyName(@PathVariable("supplierId") Long productId);
}