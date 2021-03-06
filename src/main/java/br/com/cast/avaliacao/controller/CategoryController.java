package br.com.cast.avaliacao.controller;

import br.com.cast.avaliacao.exception.ResourceNotFoundException;
import br.com.cast.avaliacao.model.entity.Category;
import br.com.cast.avaliacao.model.response.CategoryResponse;
import br.com.cast.avaliacao.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.com.cast.avaliacao.controller.category.CategoryEndPoints.*;

@RestController
@RequestMapping(CATEGORIES)
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = GET_ALL)
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping(CREATE)
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @PutMapping(UPDATE_BY_CATEGORY_ID)
    public CategoryResponse updateCategoryDescription(
            @PathVariable Long categoryId,
            @Valid @RequestBody String description
    ) throws ResourceNotFoundException {
        return categoryService.updateCategoryDescription(categoryId, description);
    }

    @DeleteMapping(DELETE_BY_CATEGORY_ID)
    public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
