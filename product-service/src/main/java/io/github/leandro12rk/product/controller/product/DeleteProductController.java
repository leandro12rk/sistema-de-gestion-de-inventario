package io.github.leandro12rk.product.controller.product;


import io.github.leandro12rk.product.repository.product.ProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteProductController {
    private final ProductRepository productRepository;

    public DeleteProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return "Producto con ID " + productId + " eliminado correctamente.";
        }).orElse("Error: No se encontró el producto con ID " + productId);
    }
}
