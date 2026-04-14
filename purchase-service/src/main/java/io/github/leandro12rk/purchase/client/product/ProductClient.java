package io.github.leandro12rk.purchase.client.product;

import io.github.leandro12rk.purchase.dto.product.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service" , contextId = "productClient")
public interface ProductClient {
    @GetMapping("/product/{productId}/name")
    ProductDTO getProductName(@PathVariable("productId") Long productId);
}