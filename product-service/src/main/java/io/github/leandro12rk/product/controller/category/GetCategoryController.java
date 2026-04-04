package io.github.leandro12rk.product.controller.category;


import io.github.leandro12rk.product.model.category.Category;
import io.github.leandro12rk.product.repository.category.CategoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class GetCategoryController {
    CategoryRepository categoryRepository;

    public GetCategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @GetMapping("/")
    public List<Category> getAllCategory(){
       return  categoryRepository.findAll();
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable("categoryId") Long categoryId){
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
