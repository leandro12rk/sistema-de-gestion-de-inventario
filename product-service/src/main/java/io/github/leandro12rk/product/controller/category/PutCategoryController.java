package io.github.leandro12rk.product.controller.category;

import io.github.leandro12rk.product.model.category.Category;
import io.github.leandro12rk.product.repository.category.CategoryRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
public class PutCategoryController {

    CategoryRepository categoryRepository;

    public PutCategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category UpdateCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }
}
