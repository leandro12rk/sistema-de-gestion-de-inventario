package io.github.leandro12rk.product.dto.product;

import lombok.Data;

@Data
public class ProductFilterDTO {
    private String productName;
    private String sku;
    private String categoryName;
    private String supplierName;
    private Boolean status;
}