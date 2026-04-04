package io.github.leandro12rk.product.controller.category;

import io.github.leandro12rk.product.model.category.Category;
import io.github.leandro12rk.product.repository.category.CategoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PostCategoryController {

    CategoryRepository categoryRepository;

    public PostCategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @PostMapping("/")
    public Category CreateCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
