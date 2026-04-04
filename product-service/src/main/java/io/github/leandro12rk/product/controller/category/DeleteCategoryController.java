package io.github.leandro12rk.product.controller.category;

import io.github.leandro12rk.product.repository.category.CategoryRepository;
import io.github.leandro12rk.product.repository.product.ProductRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class DeleteCategoryController {
    CategoryRepository categoryRepository;

    public DeleteCategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @DeleteMapping("/{categoryId}")
    public void deleteProduct(@PathVariable("categoryId") Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
