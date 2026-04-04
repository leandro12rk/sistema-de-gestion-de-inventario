package io.github.leandro12rk.product.controller.product;


import io.github.leandro12rk.product.model.product.Product;
import io.github.leandro12rk.product.repository.product.ProductRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostProductController {

    private final ProductRepository productRepository;


    public PostProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }


}
