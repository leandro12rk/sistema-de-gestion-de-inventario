package io.github.leandro12rk.product.controller.product;

import io.github.leandro12rk.product.projection.product.ProductGetProjection;
import io.github.leandro12rk.product.repository.product.ProductRepository; // Ojo a la escritura de "repository"
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list-product")
public class GetProductController {

    private final ProductRepository productRepository;


    public GetProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 1. Obtener todos los productos
    @GetMapping("/")
    public List<ProductGetProjection> getAllProducts() {
        return productRepository.findAllProjectedBy();
    }

    // 2. Obtener un producto por ID
    @GetMapping("/{productId}")
    public ResponseEntity<ProductGetProjection> getProductById(@PathVariable Long productId) {
        return productRepository.findProjectedById(productId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}