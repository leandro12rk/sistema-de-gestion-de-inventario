package io.github.leandro12rk.product.controller.product;

import io.github.leandro12rk.product.dto.product.ProductFilterDTO;
import io.github.leandro12rk.product.model.product.ProductModel;
import io.github.leandro12rk.product.projection.product.ProductGetProjection;
import io.github.leandro12rk.product.projection.product.ProductNameProjection;
import io.github.leandro12rk.product.repository.product.ProductRepository;
import io.github.leandro12rk.product.service.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //  Get All Products
    @GetMapping
    public ResponseEntity<List<ProductGetProjection>> getAllProducts(@ModelAttribute ProductFilterDTO filter) {
        return ResponseEntity.ok(productService.getAllProducts(filter));
    }

    // Get Product Name By ID
    @GetMapping("/{productId}/name")
    public ResponseEntity<ProductNameProjection> getProductName(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductName(productId));
    }

    // Delete Product By ID
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    // Create New Product
    @PostMapping
    public ResponseEntity<ProductModel> createProduct(@Valid @RequestBody ProductModel product) {
        ProductModel created = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // Update Product By ID
    @PutMapping("/{productId}")
    public ResponseEntity<ProductModel> updateProduct(@PathVariable Long productId, @Valid @RequestBody ProductModel productDetails) {
        return ResponseEntity.ok(productService.updateProduct(productId, productDetails));
    }

}
